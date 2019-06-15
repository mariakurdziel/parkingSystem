package controllers;

import ejb.ParkingMeterManagerBean;
import ejb.UserManagerBean;
import ejb.dto.ParkingMeter;
import ejb.dto.Ticket;
import ejb.dto.Worker;
import ejb.interfaces.ParkingMeterManager;
import ejb.interfaces.UserManager;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Named("Parking")
@SessionScoped
public class ParkingMeterController implements Serializable {

    @EJB
    private ParkingMeterManager parkingManagerBean;

    @EJB
    private UserManager userManagerBean;
    private ParkingMeter parking;
    private  List<Ticket> tickets;

    @PostConstruct
    void init(){
        Worker user;
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if (principal != null) {
            user = userManagerBean.getUserbyLogin(principal.getName());
            parking=new ParkingMeterManagerBean().getParkingMeterbyId(user);
        }
        else
            parking=new ParkingMeter();
        getParkingTickets();
    }

    public void getParkingTickets(){

        tickets=parkingManagerBean.getParkingMeterTickets(parking.getId());
    }

    public ParkingMeterManager getParkingManagerBean() {
        return parkingManagerBean;
    }

    public void setParkingManagerBean(ParkingMeterManager parkingManagerBean) {
        this.parkingManagerBean = parkingManagerBean;
    }

    public ParkingMeter getParking() {
        return parking;
    }

    public void setParking(ParkingMeter parking) {
        this.parking = parking;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public UserManager getUserManagerBean() {
        return userManagerBean;
    }

    public void setUserManagerBean(UserManager userManagerBean) {
        this.userManagerBean = userManagerBean;
    }


}
