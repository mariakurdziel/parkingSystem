package dto;

import javax.persistence.*;

@Entity
@Table(name = "Tickets")
public class Ticket {

    @Id
    @Column(name="id_biletu",nullable=false)
    private long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_parkometru")
    private dto.Parkometr parkometr;

    @Column(name="cena",nullable=false)
    private String cena;

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

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }
}