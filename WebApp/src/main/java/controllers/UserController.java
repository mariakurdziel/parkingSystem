package controllers;
import ejb.dto.Worker;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import ejb.UserManagerBean;
import ejb.interfaces.remote.UserManagerRemote;


@Named("User")
@SessionScoped
public class UserController implements Serializable {

    @EJB(lookup = "java:global/EjbImpl-1.0-SNAPSHOT/UserManagerBean!ejb.interfaces.remote.UserManagerRemote")
    private UserManagerRemote userManagerBean;
    private Worker user;
    private String login;
    private String password;


    public Worker getUserbyLogin(String login){
        return userManagerBean.getUserbyLogin(login);
    }



    public Worker getUser() {
        return user;
    }

    public void setUser(Worker user) {
        this.user = user;
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

    public UserManagerRemote getUserManagerBean() {
        return userManagerBean;
    }

    public void setUserManagerBean(UserManagerRemote userManagerBean) {
        this.userManagerBean = userManagerBean;
    }
}