package dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parkometr")
public class Parkometr {

    @Id
    @Column(name="id_parkometru",nullable=false)
    private long id;

    @OneToMany(mappedBy = "parkometr",cascade= CascadeType.ALL)
    private List<Worker> workers;

    @OneToMany(mappedBy = "parkometr",cascade= CascadeType.ALL)
    private List<Ticket> tickets;

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
}