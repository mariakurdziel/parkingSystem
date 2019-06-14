package controllers;
import ejb.dto.Worker;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;

import ejb.UserManagerBean;
import ejb.interfaces.UserManager;
import ejb.interfaces.remote.UserManagerRemote;


@Named("User")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private UserManager userManagerBean;
    private static Worker user;
    private static String login;
    private static String password;


    @PostConstruct
    public void init(){
        if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = userManagerBean.getUserbyLogin(principal.getName());
            }
        }

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

    public UserManager getUserManagerBean() {
        return userManagerBean;
    }

    public void setUserManagerBean(UserManager userManagerBean) {
        this.userManagerBean = userManagerBean;
    }


}
