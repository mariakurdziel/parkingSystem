package dto;

import javax.persistence.*;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @Column(name="worker_id",nullable=false)
    private long id;

    @Column(name="name",nullable=false)
    private String name;

    @Column(name="surname",nullable=false)
    private String surname;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "meter_id")
    private Parkometr parkometr;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Parkometr getParkometr() {
        return parkometr;
    }

    public void setParkometr(Parkometr parkometr) {
        this.parkometr = parkometr;
    }


}