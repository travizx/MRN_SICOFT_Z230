package com.mx.teknei.mrn.socket.server.tcp;

import com.mx.teknei.mrn.DTO.TramaINITDTO;
import com.mx.teknei.mrn.utils.DecodeTrama;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author HEYDRICH ABRAHAM ENCISO - [haenciso@teknei.com.mx]
 * @date 23/09/2015
 * @class ServSocketT
 */
public class ServSocketT {

//    public static void main(String[] args) {
//        // declaramos un objeto ServerSocket para realizar la comunicación
//        ServerSocket socket_serv;
//        // creamos una varible boolean con el valor a false
//        boolean fin = false;
//         TramaINITDTO tramDTO;
//        // Declaramos un bloque try y catch para controlar la ejecución del subprograma
//        try {
//            // Instanciamos un ServerSocket con la dirección del destino y el
//            // puerto que vamos a utilizar para la comunicación
//            socket_serv = new ServerSocket(5410);
//            // Creamos un socket_cli al que le pasamos el contenido del objeto socket después
//            // de ejecutar la función accept que nos permitirá aceptar conexiones de clientes
//            do {
//                Socket socket_cli = socket_serv.accept();
//                // Declaramos e instanciamos el objeto DataInputStream
//                // que nos valdrá para recibir datos del cliente
//                DataInputStream in = new DataInputStream(socket_cli.getInputStream());
//                // Creamos un bucle do while en el que recogemos el mensaje
//                // que nos ha enviado el cliente y después lo mostramos
//                // por consola
//                String mensaje = "";
//                mensaje = in.readUTF();
//                System.out.println(mensaje);
//                if (!mensaje.isEmpty()) {
//                    DecodeTrama decTram = new DecodeTrama(mensaje);
//                    System.out.println("CorrectTrama:"+decTram.isCorrectDecoTrama());
//                    System.out.println("Es FIN:"+decTram.isFinFistValue() );
//                    if (!decTram.isFinFistValue()) {
//                       tramDTO = decTram.getTramaDTO();
//                        System.out.println("ID Ve:"+tramDTO.getID_VEHI());
//                        System.out.println("HOR:"+tramDTO.getHORA_RECV());
//                    }else{
//                        System.out.println("Es es el FIN");
//                    }
//                } else {
//                    System.out.println("Trama viene vacia.");
//                }
//                //****************ENCIAR MENSAJE de OK
//                DataOutputStream out = new DataOutputStream(socket_cli.getOutputStream());
//                out.writeUTF("ok");
//            } while (true);
//            
//        } catch (Exception e) {
//            // si existen errores los mostrará en la consola y después saldrá del
//            // programa
//            System.err.println("Error:" + e.getMessage());
//            System.exit(1);
//        }
//
//    }
}
