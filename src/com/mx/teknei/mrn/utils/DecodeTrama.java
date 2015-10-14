
package com.mx.teknei.mrn.utils;

import com.mx.teknei.mrn.DTO.TramaCLOSEDAO;
import com.mx.teknei.mrn.DTO.TramaINITDTO;
import java.util.Date;

/**
 * @author HEYDRICH ABRAHAM ENCISO - [haenciso@teknei.com.mx]
 * @date 3/10/2015
 * @class DecodeTrama
 */
public class DecodeTrama {

    private String trama;
    private String[] decodectrama;
    private TramaINITDTO initDAO;
    private TramaCLOSEDAO closeDAO;
    private final int NUM_MIN_DATA = 4;
    private final int NUM_MAX_DATA = 3;
    private final int NUM_DATA = 4;

    public DecodeTrama(String trama) {
        //ASIGNACION DE VARIABLES
        this.trama = trama;
        //Cambiar por CONFIGURAcion---------------INIT y CLOSE
        if(trama.contains("INIT")){
            decodectrama = trama.split(",");
            if(isCorrectDecoTrama(decodectrama) ){
                //Rellenar el DTO de INCIO
                try {
                    initDAO = new TramaINITDTO();
                    initDAO.setID_RUTA(Integer.parseInt(decodectrama[1]));
                    initDAO.setID_VEHI(Integer.parseInt(decodectrama[2]));
                    initDAO.setHORA_RECV(new Date(Long.parseLong(decodectrama[3])));
                    System.out.println("DATE:"+initDAO.getHORA_RECV());
                } catch (Exception e) {
                    System.out.println("ERROR TRAMA:Viene con otros tipos de datos."+this.getClass().getSimpleName());
                }
            }else{
                System.out.println("ERROR TRAMA:NO se descompone correctamente."+this.getClass().getSimpleName());
            }   
        }else if(trama.contains("CLOSE") ){
            decodectrama = trama.split(",");
            if(isCorrectDecoTrama(decodectrama) ){
                //Rellenar el DTO de INCIO
                try {
                    closeDAO = new TramaCLOSEDAO();
                    closeDAO.setID_VEHI(Integer.parseInt(decodectrama[1]));
                    closeDAO.setID_ITIN(Integer.parseInt(decodectrama[2]));
                    closeDAO.setHORA_RECV(new Date(Long.parseLong(decodectrama[3])));
                } catch (Exception e) {
                    System.out.println("ERROR TRAMA:Viene con DATOS q no CORRESPONDEN."+this.getClass().getSimpleName());
                }
            }else{
                System.out.println("ERROR TRAMA:NO se descompone correctamente."+this.getClass().getSimpleName());
            }   
        }else{
            System.out.println("ERROR TRAMA:NO contiene ni INIT, ni CLOSE."+this.getClass().getSimpleName());
        }
    }
    

    /**
     * Funcion que Valide que contenga el numero de datos correspondientes 
     * la trama.
     * @param decodecTRAMA
     * @return 
     */
    public final boolean isCorrectDecoTrama(String[] decodecTRAMA){
        if(decodecTRAMA.length == NUM_DATA){
            return true;    
        }else{
            return false;
        }
    }

    public TramaINITDTO getInitDAO() {
        return initDAO;
    }

    public TramaCLOSEDAO getCloseDAO() {
        return closeDAO;
    }

    public String[] getDecodectrama() {
        return decodectrama;
    }
    
    
    
    
    
    
}
