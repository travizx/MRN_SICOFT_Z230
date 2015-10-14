
package com.mx.teknei.mrn.DTO;

import java.util.Date;

/**
 * @author HEYDRICH ABRAHAM ENCISO - [haenciso@teknei.com.mx]
 * @date 7/10/2015
 * @class TramaCLOSEDAO
 */
public class TramaCLOSEDAO {

    private int ID_VEHI;
    private int ID_ITIN;
    private Date HORA_RECV;

    public int getID_VEHI() {
        return ID_VEHI;
    }

    public void setID_VEHI(int ID_VEHI) {
        this.ID_VEHI = ID_VEHI;
    }
    

    public int getID_ITIN() {
        return ID_ITIN;
    }

    public void setID_ITIN(int ID_VEHI) {
        this.ID_ITIN = ID_VEHI;
    }

    public Date getHORA_RECV() {
        return HORA_RECV;
    }

    public void setHORA_RECV(Date HORA_RECV) {
        this.HORA_RECV = HORA_RECV;
    }
    
}
