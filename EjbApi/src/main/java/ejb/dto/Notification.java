package ejb.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "notifications")
public class Notification implements Serializable {

    @Id
    @Column(name="notification_id",nullable=false)
    private long id;

    @Column(name="spot_id",nullable=false)
    private long spot_id;

    @Column(name="parkometer_id",nullable=true)
    private long parkometer_id;

    @Column(name="event_type",nullable=false)
    private String type;

    @Column(name="start_time",nullable=true)
    private Timestamp start;

    @Column(name="time_duration",nullable=true)
    private Timestamp duration;


    public Notification() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(long spot_id) {
        this.spot_id = spot_id;
    }

    public long getParkometer_id() {
        return parkometer_id;
    }

    public void setParkometer_id(long parkometer_id) {
        this.parkometer_id = parkometer_id;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Notification(long id,long spot_id, long parkometer_id, String type){
        this.id=id;
        this.spot_id=spot_id;
        this.parkometer_id=parkometer_id;
        this.type=type;
    }
}
