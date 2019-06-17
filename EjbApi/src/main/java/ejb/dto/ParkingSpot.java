package ejb.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "parkingspots")
public class ParkingSpot {

    @Id
    @Column(name="spot_id",nullable=false)
    private long id;


    @Column(name="parkometr",nullable=false)
    private long park_id;


    @Column(name="reserved",nullable=false)
    private boolean reserved=false;

    @Column(name="is_paid",nullable=false)
    private boolean is_paid=false;

    @Column(name="start_time",nullable=true)
    private Timestamp start_time;

    @Column(name="duration",nullable=true)
    private Timestamp duration;



    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "meter_id")
    private ParkingMeter parkometr;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public ParkingMeter getParkometr() {
        return parkometr;
    }

    public void setParkometr(ParkingMeter parkometr) {
        this.parkometr = parkometr;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    public ParkingSpot(){}

    public ParkingSpot(Long spot_id, Long park_id, boolean reserved,boolean is_paid, Timestamp start_time, Timestamp duration){
        this.park_id=park_id;
        this.id=spot_id;
        this.reserved=reserved;
        this.is_paid=is_paid;
        this.start_time=start_time;
        this.duration=duration;
    }

    public long getPark_id() {
        return park_id;
    }

    public void setPark_id(long park_id) {
        this.park_id = park_id;
    }

}
