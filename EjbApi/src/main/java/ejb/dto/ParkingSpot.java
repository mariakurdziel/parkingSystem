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
}
