package ejb.dto;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(name="ticket_id",nullable=false)
    private long id;

    @Column(name="parking_spot",nullable=false)
    private long spot_id;


   @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "spot_id")
    private ParkingSpot spot;

    @Column(name="price",nullable=false)
    private double price;

    @Column(name="start_time",nullable=false)
    private Date start_time;

    @Column(name="duration",nullable=false)
    private Time duration;

    @Column(name="parking_id",nullable=false)
    private long id_parkingu;

    @Column(name="registration_number",nullable=false)
    private String registration_number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

   public ParkingSpot getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public long getId_parkingu() {
        return id_parkingu;
    }

    public void setId_parkingu(long id_parkingu) {
        this.id_parkingu = id_parkingu;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public long getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(long spot_id) {
        this.spot_id = spot_id;
    }

    public Ticket(){}

    public Ticket(long ticket_id,long parking_spot,long id_parkingu,double price,String registration_number, Date start_time, Time duration){
        this.id=ticket_id;
        this.registration_number=registration_number;
        this.spot_id=parking_spot;
        this.price=price;
        this.start_time=start_time;
        this.duration=duration;
        this.id_parkingu=id_parkingu;
    }
}
