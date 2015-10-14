
package com.mx.teknei.mrn.DTO;

import java.util.Date;

/**
 * @author HEYDRICH ABRAHAM ENCISO - [haenciso@teknei.com.mx]
 * @date 3/10/2015
 * @class Trama
 */
public class TramaINITDTO{ 
    private int ID_RUTA;
    private int ID_VEHI;
    private Date HORA_RECV;
    

    public int getID_RUTA() {
        return ID_RUTA;
    }

    public void setID_RUTA(int ID_RUTA) {
        this.ID_RUTA = ID_RUTA;
    }

    public int getID_VEHI() {
        return ID_VEHI;
    }

    public void setID_VEHI(int ID_VEHI) {
        this.ID_VEHI = ID_VEHI;
    }

    public Date getHORA_RECV() {
        return HORA_RECV;
    }

    public void setHORA_RECV(Date HORA_RECV) {
        this.HORA_RECV = HORA_RECV;
    }

    
    
    
}
