package com.mx.teknei.mrn.socket.server.tcp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HEYDRICH ABRAHAM ENCISO - [haenciso@teknei.com.mx]
 * @date 3/10/2015
 * @class prueba
 */
public class prueba {

//    public static void main(String[] args) {
        //Servicio horario operativo
//        List<SfmoHoraSeop> horaOperLista = new SfmoHoraSeopService().ListarHoraSeOp();
//        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
//        DateUtil util = new DateUtil();
//        for (SfmoHoraSeop horaOperLista1 : horaOperLista) {
//            System.out.println("------------------------------------------------");
//            /////////////////////////----inicio----------------
//            System.out.println("1:-"+horaOperLista1.getHoraInicOper());
//            System.out.println("1----Hora Mas Cinto"+util.sumarRestarHorasFecha(horaOperLista1.getHoraInicOper(), 5).toString());
//            System.out.println("1----Hora MENIOS Cinto"+util.sumarRestarHorasFecha(horaOperLista1.getHoraInicOper(), -5).toString());
////            String[] dia = horaOperLista1.getHoraInicOper().toString().split(" ");
////            System.out.println(dia[0]);
////            System.out.println(dia[1]);
////            Date dat1;
////            try {
////                dat1 = dateFormat.parse(dia[1]);
////            } catch (ParseException ex) {
////                System.out.println("Error en Date [1]");
////                dat1 = new Date();
////            }
//            
//            ////////////////////////------fin-----------------
//            System.out.println("2:-"+horaOperLista1.getHoraFinOper());
////            String[] dia2 = horaOperLista1.getHoraFinOper().toString().split(" ");
////            System.out.println(dia2[0]);
////            System.out.println(dia2[1]);
////            Date dat2;
////            try {
////                dat2 = dateFormat.parse(dia2[1]);
////            } catch (ParseException ex) {
////                System.out.println("Error en data2 [1]");
////                dat2 = new Date();
////            }
//            ///////////////////////-----fecha actual-----------------
//            Date fechaActualORI = new Date();
//            fechaActualORI.setHours(03);
//            fechaActualORI.setMinutes(00);
//            fechaActualORI.setSeconds(00);
//            System.out.println("3:-"+fechaActualORI);
////            try {
////                fechaActualORI = dateFormat.parse("14:30:00");
////                System.out.println("3:-"+fechaActualORI);
////            } catch (ParseException ex) {
////                System.out.println("Error en Date [1]");
////                fechaActualORI = new Date();
////            }
//            System.out.println("REGRESA:"+util.comparetoHorsOpe(horaOperLista1.getHoraInicOper(), horaOperLista1.getHoraFinOper(), fechaActualORI));
//            Date dateOne = util.sumarRestarHorasFecha(fechaActualORI, 5);
////            System.out.println("----Hora Mas Cinto"+dateOne.toString());
////            Date dateTwo = util.sumarRestarHorasFecha(fechaActualORI, -5);
////            System.out.println("----Hora MENIOS Cinto"+dateTwo.toString());
////            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            try {
////                Date fromDate = df.parse(dateOne.toString());
////                System.out.println("+++Hora1:"+fromDate);
////                Date toDate = df.parse(dateTwo.toString());
////                System.out.println("+++Hora2:"+toDate);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//        }
// ********************************************************************************************************************************
//        Date fechaActual = new Date();
//        fechaActual.setDate(07);
//        fechaActual.setHours(05);
//        System.out.println(fechaActual);
//        Date fechaMasCinco = new DateUtil().sumarRestarHorasFecha(fechaActual, 5);
//        Date fechaMenosCinco = new DateUtil().sumarRestarHorasFecha(fechaActual, -5);
//        ISfpfItinService itinService = new SfpfItinService();
//        List<SfpfItin> itinList = itinService.ObtenerItinEntreRangoFecha(new DateUtil().convertirFormatoDate(fechaMenosCinco),new DateUtil().convertirFormatoDate(fechaMasCinco),2,1 );
//        System.out.println("NO:"+itinList.size());
//        for (SfpfItin itinList1 : itinList) {
//            System.out.println(itinList1.getIdItin());
//        }
//        System.out.println("1:"+itinList.get(0).getIdItin());
//////**************************************************************************************
//        ISfpfItinService itinService = new SfpfItinService();
//        SfpfItin intiEnty = itinService.BuscarItinPorID(2);
//        System.out.println("INCIO:"+intiEnty.getIdItin());
        
//        Date nuevoDia = new Date(2015, 9, 12, 01, 12);
//        Date nuevoDia = new Date();
//        System.out.println("Dia de hoy:" + nuevoDia);
//        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
//        Date fechaActual;
//        try {
//            fechaActual = dateFormat.parse(dateFormat.format(nuevoDia));
//            System.out.println("Dia con FORMATO:"+fechaActual);
//        } catch (ParseException ex) {
//            System.out.println("Error en dia ahora");
//            fechaActual = new Date();
//        }
//
//    }

}
