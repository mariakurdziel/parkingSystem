package ejb.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parkingspots")
public class ParkingSpot {

    @Id
    @Column(name="spot_id",nullable=false)
    private long id;

    @Column(name="reserved",nullable=false)
    private boolean reserved=false;

    @Column(name="parkometr",nullable=false)
    private long park_id;

   @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "meter_id")
    private ParkingMeter parkometr;

    @OneToMany(mappedBy = "spot",cascade= CascadeType.ALL)
    private List<Ticket> tickets;

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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public ParkingSpot(){}

    public ParkingSpot(Long spot_id, Long park_id, boolean reserved){
        this.park_id=park_id;
        this.id=spot_id;
        this.reserved=reserved;
    }

    public long getPark_id() {
        return park_id;
    }

    public void setPark_id(long park_id) {
        this.park_id = park_id;
    }
}
