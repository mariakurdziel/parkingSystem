package ejb.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workers")
public class Worker implements Serializable {

    @Id
    @Column(name="worker_id",nullable=false)
    private long id;

    @Column(name="parkometr",nullable=false)
    private long meter;

    @Column(name="name",nullable=false)
    private String name;

    @Column(name="surname",nullable=false)
    private String surname;

    @Column(name="login",nullable=false)
    private String login;

    @Column(name="admin_credentials",nullable=false)
    private boolean is_admin;

    public Worker(Long id, Long meter_id,String name, String surname, String username, boolean admincredentials) {

        this.id=id;
        this.meter=meter_id;
        this.name=name;
        this.surname=surname;
        this.login=username;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public long getMeter() {
        return meter;
    }

    public void setMeter(long meter) {
        this.meter = meter;
    }

    public Worker(){}
}