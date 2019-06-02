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

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "spot_id")
    private ParkingSpot spot;

    @Column(name="price",nullable=false)
    private double price;

    @Column(name="start_time",nullable=false)
    private Date start_time;

    @Column(name="duration",nullable=false)
    private Time duration;

    @Column(name="is_paid",nullable=false)
    private boolean is_paid;

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

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }
}
