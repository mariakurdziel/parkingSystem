package controllers;

import ejb.JMSHandler;
import ejb.dto.Notification;
import ejb.dto.ParkingSpot;
import ejb.dto.SpotStatus;
import ejb.interfaces.JMSHandlerManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Named("Spot")
@SessionScoped
public class SpotController implements Serializable {

    private ParkingSpot spot;
    private static boolean is_reserved=false;
    private static boolean is_paid=false;

    @EJB
    JMSHandlerManager jmsHandler;

    public void reserve(ParkingSpot spot){
        long id=new Date().getTime();
        String ident=new Long(id).toString();
        Notification not=new Notification(id,spot.getId(),spot.getPark_id(),"W");
        not.setStart(new Timestamp(new java.util.Date().getTime()));
        jmsHandler.sendMsg(not,ident);
        SpotStatus stat=(SpotStatus)jmsHandler.receiveMsg(ident);
        FacesContext fsc=FacesContext.getCurrentInstance();
        FacesMessage fm=new FacesMessage();
        if ( stat.getError() != null ) {
            fm.setSummary(stat.getError());
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fsc.addMessage(null, fm);
        } else {
            this.spot = spot;
            is_reserved = stat.isReserved();
            this.spot.setReserved(true);
            this.spot.setIs_paid(false);
            this.spot.setStart_time(not.getStart());
            fm.setSummary("Zarejestrowano wjazd!");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fsc.addMessage(null, fm);
        }

    }

    public void pay(ParkingSpot spot){
        long id=new Date().getTime();
        String ident=new Long(id).toString();
        Notification not=new Notification(id,spot.getId(),spot.getPark_id(),"O");
        Calendar cal= Calendar.getInstance();
        if ( spot.getStart_time() != null )
            cal.setTime(spot.getStart_time());
        else {
            cal.setTime(new java.util.Date());
            not.setStart(new Timestamp(cal.getTimeInMillis()));
        }
        cal.add(Calendar.HOUR, 1);
        not.setDuration(new Timestamp(cal.getTimeInMillis()));
        jmsHandler.sendMsg(not,ident);
        SpotStatus stat=(SpotStatus)jmsHandler.receiveMsg(ident);

        FacesContext fsc=FacesContext.getCurrentInstance();
        FacesMessage fm=new FacesMessage();
        if ( stat.getError() != null ) {
            fm.setSummary(stat.getError());
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fsc.addMessage(null, fm);
        } else {
            this.spot = spot;
            is_paid=stat.isPaid();
            is_reserved = stat.isReserved();
            this.spot.setReserved(true);
            this.spot.setIs_paid(true);
            this.spot.setDuration(not.getDuration());
            fm.setSummary("Zarejestrowano wpłatę!");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fsc.addMessage(null, fm);
        }


    }


    public void free(ParkingSpot spot){
        long id=new Date().getTime();
        String ident=new Long(id).toString();
        Notification not=new Notification(id,spot.getId(),spot.getPark_id(),"Z");
        jmsHandler.sendMsg(not,ident);
        SpotStatus stat=(SpotStatus)jmsHandler.receiveMsg(ident);

        FacesContext fsc=FacesContext.getCurrentInstance();
        FacesMessage fm=new FacesMessage();
        if ( stat.getError() != null ) {
            fm.setSummary(stat.getError());
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fsc.addMessage(null, fm);
        } else {
            this.spot = spot;
            is_paid=stat.isPaid();
            is_reserved=stat.isReserved();;
            is_reserved = stat.isReserved();
            this.spot.setReserved(false);
            this.spot.setIs_paid(false);
            this.spot.setStart_time(null);
            this.spot.setDuration(null);
            fm.setSummary("Zarejestrowano wyjazd!");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fsc.addMessage(null, fm);
        }
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }

    public static boolean isIs_reserved() {
        return is_reserved;
    }

    public static void setIs_reserved(boolean is_reserved) {
        SpotController.is_reserved = is_reserved;
    }

    public static boolean isIs_paid() {
        return is_paid;
    }

    public static void setIs_paid(boolean is_paid) {
        SpotController.is_paid = is_paid;
    }

}
