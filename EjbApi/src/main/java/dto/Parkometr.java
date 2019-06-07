package dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parkingmeters")
public class Parkometr {

    @Id
    @Column(name="meter_id",nullable=false)
    private long id;

    @OneToMany(mappedBy = "meter_id",cascade= CascadeType.ALL)
    private List<Worker> workers;

    @OneToMany(mappedBy = "meter_id",cascade= CascadeType.ALL)
    private List<Ticket> tickets;

    @Column(name="address",nullable=false)
    private String address;

    @Column(name="hour_price",nullable=false)
    private Double hourPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(Double hourPrice) {
        this.hourPrice = hourPrice;
    }
}