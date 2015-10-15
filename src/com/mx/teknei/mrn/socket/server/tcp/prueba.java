package com.mx.teknei.mrn.socket.server.tcp;

import com.mx.teknei.pcabordo.lib.entities.SfmoHistReceNave;
import com.mx.teknei.pcabordo.lib.service.impl.RecpNavHistService;
import java.util.Date;
import java.util.List;

/**
 * @author HEYDRICH ABRAHAM ENCISO - [haenciso@teknei.com.mx]
 * @date 3/10/2015
 * @class prueba
 */
public class prueba {

//    public static void main(String[] args) {
//        //Llegada
//        Date horaLLegReal = new Date();
//        horaLLegReal.setHours(17);
//        horaLLegReal.setMinutes(00);
//        horaLLegReal.setSeconds(00);
//        System.out.println("Hora LLEGADA:" + horaLLegReal);
//        //Salida
//        Date horaSaliReal = new Date();
//        horaSaliReal.setHours(18);
//        horaSaliReal.setMinutes(00);
//        horaSaliReal.setSeconds(00);
//        System.out.println("Hora SALIDA:" + horaSaliReal);
//        /////Mandar a llamar el METODO POMPIS
//        double varKM = KilometrosRecorridos(horaSaliReal.getTime(), horaLLegReal.getTime(), 111);
//        System.out.println("KM--TOTAL" + varKM);
//
//    }
//
//    public static Double KilometrosRecorridos(Long salidaReal, Long llegadaReal, int ID_VEHI) {
//        //KILOMETROS RECORRIDOS QUE REGRESA 
//        double kilometrosReco = 0;
//
//        RecpNavHistService histService = new RecpNavHistService();
//        List<SfmoHistReceNave> itemTotalReco;
//        itemTotalReco = histService.listCicloReco(salidaReal, llegadaReal, ID_VEHI);
//        System.out.println("NO. del HIST_RECV_NAVE EN ESA FECHA:" + itemTotalReco.size());
//
//        if (itemTotalReco.size() >= 2) {
//            double lat3 = 0;
//            double longt3 = 0;
////            double lat4 = 0;
////            double longt4 = 0;
//            double aux = 0;
//            double limit = 0.10;
//
//            for (SfmoHistReceNave historialRECVNAV : itemTotalReco) {
//                if (lat3 == 0 && longt3 == 0) {
//                    lat3 = historialRECVNAV.getLatiReceNave();
//                    longt3 = historialRECVNAV.getLongReceNave();
//                } else {
//                    aux = calcularDistanciaEntreDosPuntos(
//                            lat3,longt3,historialRECVNAV.getLatiReceNave(),historialRECVNAV.getLongReceNave() ) ;
//                    lat3 = historialRECVNAV.getLatiReceNave();
//                    longt3 = historialRECVNAV.getLongReceNave();
//                }
//                kilometrosReco += aux;
//                System.out.println("AUX:" + kilometrosReco);
//                
//            }
//
//        } else {
//            return 0.0;
//        }
//        return kilometrosReco;
//    }
//
//    public static double calcularDistanciaEntreDosPuntos(double lat1, double lng1,
//            double lat2, double lng2) {
//        double earthRadius = 6371; // Radio de la tierra en kilometros
//        lat1 = Math.toRadians(lat1);
//        lng1 = Math.toRadians(lng1);
//        lat2 = Math.toRadians(lat2);
//        lng2 = Math.toRadians(lng2);
//        double dLon = (lng2 - lng1);
//        double dLat = (lat2 - lat1);
//        double sinlat = Math.sin(dLat / 2);
//        double sinlon = Math.sin(dLon / 2);
//        double a = (sinlat * sinlat) + Math.cos(lat1) * Math.cos(lat2)
//                * (sinlon * sinlon);
//        double c = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));
//        double distancia = earthRadius * c * 1000;
//
//        return distancia;
//    }

}
