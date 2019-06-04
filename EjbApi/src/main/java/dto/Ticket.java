package dto;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(name="ticket_id",nullable=false)
    private long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "meter_id")
    private dto.Parkometr parkometr;

    @Column(name="price",nullable=false)
    private Double price;

    @Column(name="registration__number",nullable=false)
    private String registrationNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public dto.Parkometr getParkometr() {
        return parkometr;
    }

    public void setParkometr(dto.Parkometr parkometr) {
        this.parkometr = parkometr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String number) {
        this.registrationNumber = number;
    }
}