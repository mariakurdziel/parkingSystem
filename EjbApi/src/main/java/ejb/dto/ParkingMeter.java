package ejb.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parkingmeters")
public class ParkingMeter {

    @Id
    @Column(name="meter_id",nullable=false)
    private long id;

   @OneToMany(mappedBy = "parkometr",cascade= CascadeType.ALL)
    private List<ParkingSpot> spots;

    @Column(name="hour_price",nullable=false)
    private double price;

    @Column(name="address",nullable=false)
    private String location;

    public  long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ParkingMeter(Long id, double hour_price, String address){
        this.id=id;
        this.price=hour_price;
        this.location=address;
    }

    public ParkingMeter(){}
}
