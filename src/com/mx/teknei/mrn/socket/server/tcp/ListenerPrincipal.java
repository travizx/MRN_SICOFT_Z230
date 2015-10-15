package com.mx.teknei.mrn.socket.server.tcp;

import com.mx.teknei.mrn.DTO.TramaCLOSEDAO;
import com.mx.teknei.mrn.DTO.TramaINITDTO;
import com.mx.teknei.mrn.utils.DateUtil;
import com.mx.teknei.mrn.utils.DecodeTrama;
import com.mx.teknei.pcabordo.lib.entities.SbctAlar;
import com.mx.teknei.pcabordo.lib.entities.SfesEst;
import com.mx.teknei.pcabordo.lib.entities.SfmoHistReceNave;
import com.mx.teknei.pcabordo.lib.entities.SfmoHoraSeop;
import com.mx.teknei.pcabordo.lib.entities.SfpfItin;
import com.mx.teknei.pcabordo.lib.entities.SfruRuta;
import com.mx.teknei.pcabordo.lib.entities.SfvhVehi;
import com.mx.teknei.pcabordo.lib.service.ISfpfItinService;
import com.mx.teknei.pcabordo.lib.service.impl.RecpNavHistService;
import com.mx.teknei.pcabordo.lib.service.impl.SbctAlarService;
import com.mx.teknei.pcabordo.lib.service.impl.SfesEstaService;
import com.mx.teknei.pcabordo.lib.service.impl.SfmoHoraSeopService;
import com.mx.teknei.pcabordo.lib.service.impl.SfpfItinService;
import com.mx.teknei.pcabordo.lib.service.impl.SfruRutaService;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;

/**
 * @author HEYDRICH ABRAHAM ENCISO - [haenciso@teknei.com.mx]
 * @date 2/10/2015
 * @class ListenerPrincipal
 */
public class ListenerPrincipal {

    private List<SfesEst> estacionLista;
    private List<SfruRuta> rutaLista;
    private List<SbctAlar> alarmasLista;
    private List<SfmoHoraSeop> horaOperLista;
    //Creamos el hilo de Socket Server para una comunicacion
    private ServerSocket socket_serv;
    //Objeto DTO que tra trama
    TramaINITDTO T_INIT_DTO;
    TramaCLOSEDAO T_CLOSE_DTO;
    //VALORES por Defecto
    private final int CATA_ACT = 1;
    private final int CATA_OPER = 514;
    private final int CATA_CERR = 515;

    public ListenerPrincipal() {
        init();
        initSocket();
    }

    private void init() {
        //Absorber los datos de BD
        estacionLista = new SfesEstaService().ListarEstaciones();
        rutaLista = new SfruRutaService().ListarRutas();
        alarmasLista = new SbctAlarService().ListarAlarmas();
        horaOperLista = new SfmoHoraSeopService().ListarHoraSeOp();
        System.out.println("************INICIO SERVER SOCKET ****************");
    }

    private void initSocket() {
        // Declaramos un bloque try y catch para controlar la ejecución del subprograma
        try {
            // Instanciamos un ServerSocket con la dirección del destino y el
            // puerto que vamos a utilizar para la comunicación
            socket_serv = new ServerSocket(5410);
            // Creamos un socket_cli al que le pasamos el contenido del objeto socket después
            // de ejecutar la función accept que nos permitirá aceptar conexiones de clientes
            do {
                System.out.println("----------Esperando cliente-------");
                Socket socket_cli = socket_serv.accept();
                // Declaramos e instanciamos el objeto DataInputStream
                // que nos valdrá para recibir datos del cliente
                DataInputStream in = new DataInputStream(socket_cli.getInputStream());
                // Creamos un bucle do while en el que recogemos el mensaje
                // que nos ha enviado el cliente y después lo mostramos
                // por consola
                //Mensaje del Socket Cliente
                String msgSocket = in.readUTF();
                System.out.println("+TRAMA SOCKET CLIENTE:" + msgSocket);
                //Verificar que contiene la TRAMA
                revisarTRAMA(msgSocket, socket_cli);
                //************************************
                //****************ENVIAR MENSAJE de OK
                socket_cli.close();
//                DataOutputStream out = new DataOutputStream(socket_cli.getOutputStream());
//                out.writeUTF("ok");
            } while (true);

        } catch (Exception e) {
            // si existen errores los mostrará en la consola y después saldrá del
            // programa
            System.err.println("Error:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void revisarTRAMA(String msgRecibido, Socket socket_cli) {
        //Comprobar que esta vacío el String que manda desde el Socket Cliente
        if (!msgRecibido.isEmpty() || !msgRecibido.equals("")) {
            if (msgRecibido.contains("INIT")) {
                //Si trae algo Deformar la Trama
                DecodeTrama decTram = new DecodeTrama(msgRecibido);
                T_INIT_DTO = decTram.getInitDAO();
                if (T_INIT_DTO != null) {
                    System.out.println("++IDVEHI:"+T_INIT_DTO.getID_VEHI());
                    System.out.println("++IDRUTA:"+T_INIT_DTO.getID_RUTA());
                    System.out.println("++DATE:"+T_INIT_DTO.getHORA_RECV());
                    //COMPARAR HORARIOS OPERATIVOS
                    if( checkHorariosOpe(T_INIT_DTO.getHORA_RECV()) ){
                        //COMPARAR CON ITINERARIO
                        //AUMENTO LA FECHA MAS MENOS CINCO A LA FECHA ACTUAL
                        Date fechaActual = T_INIT_DTO.getHORA_RECV();
                        Date fechaMasCinco = new DateUtil().sumarRestarHorasFecha(fechaActual, 5);
                        Date fechaMenosCinco = new DateUtil().sumarRestarHorasFecha(fechaActual, -5);
                        ISfpfItinService itinService = new SfpfItinService();
                        List<SfpfItin> itinList;
                        itinList = itinService.ObtenerItinEntreRangoFecha(//Siempre en ese orden menor mayor
                                    new DateUtil().convertirFormatoDate(fechaMenosCinco),
                                    new DateUtil().convertirFormatoDate(fechaMasCinco),
                                    T_INIT_DTO.getID_RUTA(), CATA_ACT );
                        System.out.println("++++NUMEROS DE ITINERARIOS:"+itinList.size());
                        if(itinList == null || itinList.isEmpty() ){//Si TIENE TRAJO ALGUN INTINERARIO ACTIVO
                            mandarMensajeError("ERROR:NO hay Itinerarios para asignar por el Momento", socket_cli);
                        } else {//GUARDAR EN BD ITINERARIO
                            SfpfItin itiEnty = itinList.get(0);
                            SfvhVehi vehiEntity = new SfvhVehi();
                            vehiEntity.setIdVehi(T_INIT_DTO.getID_VEHI());
                            itiEnty.setSfvhVehi(vehiEntity);
                            itiEnty.setIdEsta(CATA_OPER);//514
                            itiEnty.setDireItin("NORTE");
                            itiEnty.setUsrModi("SICOFT");
                            itiEnty.setFchModi( new Date() );
                            itiEnty.setHoraSaliRealItin(T_INIT_DTO.getHORA_RECV());
                            itinService.ActivarItinera(itiEnty);
                            mandarMensajeOK(String.valueOf(itiEnty.getIdItin()),socket_cli);
                            //MANDA TODO OK
                            System.out.println("-----Se Asigno el ITIN:"+String.valueOf(itiEnty.getIdItin()));
                        }
                    }else{
                       //Mandar mensaje de error por que no esta en el rango de HORARIO OPERATIVO
                       mandarMensajeError("ERROR: Aun no es Horario de Operación.Intentelo más tarde.",socket_cli);
                    }
                } else {//Error con trama 
                    mandarMensajeError(socket_cli);
                    System.out.println("ERROR DESCOMPONER DATOS:T_INIT_DTO null."+this.getClass().getSimpleName());
                }
            }else if (msgRecibido.contains("CLOSE")) {//************************************************************
                //Si trae algo Deformar la Trama
                DecodeTrama decTram = new DecodeTrama(msgRecibido);
                T_CLOSE_DTO = decTram.getCloseDAO();
                if (T_CLOSE_DTO != null) {
                    System.out.println("++IDVEHI:"+T_CLOSE_DTO.getID_VEHI());
                    System.out.println("++IDITIN:"+T_CLOSE_DTO.getID_ITIN());
                    System.out.println("++DATE:"+T_CLOSE_DTO.getHORA_RECV());
                    //Cambiar Itinerario con estos valores----
                    ISfpfItinService itinService = new SfpfItinService();
                    SfpfItin intiEnty = itinService.BuscarItinPorID(T_CLOSE_DTO.getID_ITIN());
                    if(intiEnty != null){
                        intiEnty.setHoraLlegRealItin(T_CLOSE_DTO.getHORA_RECV());
                        intiEnty.setIdEsta(CATA_CERR);//515
                        intiEnty.setDireItin("SUR");
                        intiEnty.setUsrModi("SICOFT");
                        intiEnty.setFchModi(new Date());
                        //PONER FUNCION DEL PONY 
                        intiEnty.setKmReco( 
                                KilometrosRecorridos(intiEnty.getHoraSaliItin().getTime(), T_CLOSE_DTO.getHORA_RECV().getTime(), T_CLOSE_DTO.getID_VEHI())
                        );
                        /////////////////////////
                        itinService.CerrarItinerario(intiEnty);
                        System.out.println("-----Se Cerro un ITIN:"+String.valueOf(intiEnty.getIdItin()));
                        mandarMensajeFIN(msgRecibido, socket_cli);
//                        KilometrosRecorridos( intiEnty.getSfvhVehi().getIdVehi(), CATA_CERR );
                    }else{
                        System.out.println("ERROR ITINERARIO ENVIADO: No exite, inconsistencia en la BD."+this.getClass().getSimpleName());
                    }
                } else {//Error con trama
                    mandarMensajeError(socket_cli);
                    System.out.println("ERROR DESCOMPONER DATOS:T_INIT_DTO null."+this.getClass().getSimpleName());
                }

            } else {
                mandarMensajeError("ERROR AL MANDAR MENSAJE. Mensaje al Servidor.", socket_cli);//---
            }
        } else {
            mandarMensajeError("ERROR DE COMUNICACIÓN, Volver a intentar de nuevo.", socket_cli);//---
        }
    }

    public void mandarMensajeError(Socket socket_cli) {
        try {
            //Manda Error por que no se pudo asignar ruta
            DataOutputStream out = new DataOutputStream(socket_cli.getOutputStream());
            out.writeUTF("FATAL");
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje al conductor desde 'LISTENER'");
        }
    }

    public void mandarMensajeError(String errorMensage, Socket socket_cli) {
        try {
            //Manda Error por que no se pudo asignar ruta
            DataOutputStream out = new DataOutputStream(socket_cli.getOutputStream());
            out.writeUTF("ERROR," + errorMensage);
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje al conductor desde 'LISTENER'");
        }
    }

    public void mandarMensajeOK(String idITIN, Socket socket_cli) {
        try {
            //Manda Error por que no se pudo asignar ruta
            DataOutputStream out = new DataOutputStream(socket_cli.getOutputStream());
            out.writeUTF("OK,"+idITIN);
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje al conductor desde 'LISTENER'");
        }
    }

    public void mandarMensajeFIN(String idITIN, Socket socket_cli) {
        try {
            //Manda Error por que no se pudo asignar ruta
            DataOutputStream out = new DataOutputStream(socket_cli.getOutputStream());
            out.writeUTF("FIN");
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje al conductor desde 'LISTENER'");
        }
    }
    /**
     * Metodo que comprueba que esta entre el rango de horario de operacion
     *
     * @param date Fecha y Hora exacta en la que le dio click el conductor al
     * sistema
     * @return
     */
    public boolean checkHorariosOpe(Date date) {
        //Booleano iniciado con FALSE
        boolean checkHora = false;
        //Obtener el dia de la semana
        String diaSemana = new DateUtil().obtenerDiaSemana(date);
        //Comporbar con la lista de Horarios Operativos
        SfmoHoraSeop horaOpeEntity = null;
        for (SfmoHoraSeop horaEnty : horaOperLista) {
            if (horaEnty.getNomDia().contains(diaSemana)) {
                horaOpeEntity = horaEnty;
            }
        }
        //Checar hora de los horarios Operativos
        if (horaOpeEntity != null) {
            if (new DateUtil().comparetoHorsOpe(horaOpeEntity.getHoraInicOper(), horaOpeEntity.getHoraFinOper(), date )) {
                checkHora = true;
            } else {
                checkHora = false;
            }
        } else {
           System.out.println("ERROR BD:No se encuentra la fecha correspondiente de hoy"+this.getClass().getSimpleName());
           checkHora = false;
        }
        return checkHora;

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListenerPrincipal();
            }
        });

    }
    
    public static Double KilometrosRecorridos(Long salidaReal, Long llegadaReal, int ID_VEHI) {
        //KILOMETROS RECORRIDOS QUE REGRESA 
        double kilometrosReco = 0;

        RecpNavHistService histService = new RecpNavHistService();
        List<SfmoHistReceNave> itemTotalReco;
        itemTotalReco = histService.listCicloReco(salidaReal, llegadaReal, ID_VEHI);
        System.out.println("NO. del HIST_RECV_NAVE EN ESA FECHA:" + itemTotalReco.size());

        if (itemTotalReco.size() >= 2) {
            double lat3 = 0;
            double longt3 = 0;
            double aux = 0;

            for (SfmoHistReceNave historialRECVNAV : itemTotalReco) {
                if (lat3 == 0 && longt3 == 0) {
                    lat3 = historialRECVNAV.getLatiReceNave();
                    longt3 = historialRECVNAV.getLongReceNave();
                } else {
                    aux = calcularDistanciaEntreDosPuntos(
                            lat3,longt3,historialRECVNAV.getLatiReceNave(),historialRECVNAV.getLongReceNave() ) ;
                    lat3 = historialRECVNAV.getLatiReceNave();
                    longt3 = historialRECVNAV.getLongReceNave();
                }
                kilometrosReco += aux;
                System.out.println("AUX:" + kilometrosReco);
                
            }

        } else {
            return 0.0;
        }
        return kilometrosReco;
    }
    
    public static double calcularDistanciaEntreDosPuntos(double lat1, double lng1,
            double lat2, double lng2) {
        double earthRadius = 6371; // Radio de la tierra en kilometros
        lat1 = Math.toRadians(lat1);
        lng1 = Math.toRadians(lng1);
        lat2 = Math.toRadians(lat2);
        lng2 = Math.toRadians(lng2);
        double dLon = (lng2 - lng1);
        double dLat = (lat2 - lat1);
        double sinlat = Math.sin(dLat / 2);
        double sinlon = Math.sin(dLon / 2);
        double a = (sinlat * sinlat) + Math.cos(lat1) * Math.cos(lat2)
                * (sinlon * sinlon);
        double c = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));
        double distancia = earthRadius * c * 1000;

        return distancia;
    }

}
