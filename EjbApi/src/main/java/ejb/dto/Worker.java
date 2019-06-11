package ejb.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workers")
public class Worker implements Serializable {

    @Id
    @Column(name="worker_id",nullable=false)
    private long id;

    @Column(name="name",nullable=false)
    private String name;

    @Column(name="surname",nullable=false)
    private String surname;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "meter_id")
    private ParkingMeter parkometr;

    @Column(name="login",nullable=false)
    private String login;

    @Column(name="password",nullable=false)
    private String password;

    @Column(name="admin_credentials",nullable=false)
    private boolean is_admin;

    public Worker(Long id, Long meter_id,String name, String surname, String username, String password, boolean admincredentials) {
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.login=username;
        this.password=password;
        this.is_admin=admincredentials;
    }


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

    public ParkingMeter getParkometr() {
        return parkometr;
    }

    public void setParkometr(ParkingMeter parkometr) {
        this.parkometr = parkometr;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    Worker(){}
}