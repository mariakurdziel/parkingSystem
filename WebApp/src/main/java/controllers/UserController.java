package controllers;
import ejb.dto.Worker;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import ejb.UserManagerBean;



@Named("User")
@SessionScoped
public class UserController implements Serializable {

    @EJB(lookup = "java:global/EjbImpl-1.0-SNAPSHOT/UserManagerBean!interfaces.remotes.UserManagerRemote")
    private UserManagerBean userManagerBean;
    private Worker user;
    private String login;
    private String password;


    public Worker getUserbyLogin(String login){
        return userManagerBean.getUserbyLogin(login);
    }


    public UserManagerBean getUserManagerBean() {
        return userManagerBean;
    }

    public void setUserManagerBean(UserManagerBean userManagerBean) {
        this.userManagerBean = userManagerBean;
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
}