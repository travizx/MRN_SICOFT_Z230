package com.mx.teknei.mrn.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author HEYDRICH ABRAHAM ENCISO - [haenciso@teknei.com.mx]
 * @date 6/10/2015
 * @class DateUtil
 */
public class DateUtil {

    public DateUtil() {
    }

    /**
     * Metodo que obtiene el Dia de la Semana en la que esta la fecha que se
     * pase.
     *
     * @param date Fecha que quieres saber el dia de la Semana
     * @return String diciendote la fecha
     */
    public String obtenerDiaSemana(Date date) {
        String diaSemana = null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                diaSemana = "DOMINGO";
                break;
            case 2:
                diaSemana = "LUNES";
                break;
            case 3:
                diaSemana = "MARTES";
                break;
            case 4:
                diaSemana = "MIERCOLES";
                break;
            case 5:
                diaSemana = "JUEVES";
                break;
            case 6:
                diaSemana = "VIERNES";
                break;
            case 7:
                diaSemana = "SABADO";
                break;
            default:
                break;
        }
        return diaSemana;
    }

    /**
     * Metodo que compara si la fecha NOW esta entre el rango First y Last
     *
     * @param first Primera Fecha a comparar
     * @param last Segunda Fecha a comparar
     * @param now Fecha actual
     * @return False si no esta entre el rango y True si esta entre esas fechas
     */
    public boolean comparetoHorsOpe(Date first, Date last, Date now) {
        boolean comparacion = false;
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        /////////Convertir Primera Fecha
        String[] dia = first.toString().split(" ");
//        System.out.println(dia[1]);
        Date date1;
        try {
            date1 = dateFormat.parse(dia[1]);
        } catch (ParseException ex) {
            System.out.println("Error en convertir primera hora[1]"+this.getClass().getSimpleName());
            return comparacion = false;
        }
        date1 = convertirFechaActualDejandoHora( date1);
        System.out.println("+++FECHA 1:"+date1);
        /////////Convertir Segunda Fecha
        String[] dia2 = last.toString().split(" ");
//        System.out.println(dia2[1]);
        Date date2;
        try {
            date2 = dateFormat.parse(dia2[1]);
        } catch (ParseException ex) {
            System.out.println("Error en convertir primera hora[1]"+this.getClass().getSimpleName());
            return comparacion = false;
        }
        date2 = convertirFechaActualDejandoHora(date2);
        System.out.println("+++FECHA 2:"+date2);
        ///////////////////A Comparar 
        Date dateNow = new Date();
        System.out.println("+++FECHA ACTUAL:"+dateNow);
        //////////////////////-----Comparacion ------------------
        if ((date1.compareTo(dateNow) <= 0) && (date2.compareTo(dateNow) >= 0)) {
            System.out.println("La hora " + dateNow.toString() + " está entre "
                    + date1.toString() + " y " + date2.toString());
            return comparacion = true;
        } else {
            System.out.println("La hora " + dateNow.toString() + " no está entre " 
                    + date1.toString() + " y " + date2.toString());
            return comparacion = false;
        }
    }

    public Date sumarRestarHorasFecha(Date fecha, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MINUTE, minutes);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }
    
    public Date convertirFechaActualDejandoHora(Date fecha){
        Date fechaRegresa = new Date();
        fechaRegresa.setHours( fecha.getHours() );
        fechaRegresa.setMinutes( fecha.getMinutes() );
        fechaRegresa.setSeconds( fecha.getSeconds() );
        return fechaRegresa;
    }
    public Long convertirFormatoDate(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fromDate = null ;
        fromDate =sf.format(date);
        Date newDate = null;
        try {
            newDate = sf.parse(fromDate);
        } 
        catch (ParseException e) {
           System.out.println("ERROR FECHA:Al convertir la Fecha."+e.getMessage()+"-"+this.getClass().getSimpleName());
        }
        System.out.println("++++Fecha:"+newDate);
        if(newDate != null){
            return newDate.getTime();
        }else{
            return date.getTime();
        }
    }

}
