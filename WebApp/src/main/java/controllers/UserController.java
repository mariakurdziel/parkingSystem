package controllers;
import ejb.dto.Worker;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import ejb.UserManagerBean;
import ejb.interfaces.UserManager;
import ejb.interfaces.remote.UserManagerRemote;


@Named("User")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private UserManager userManagerBean;
    private Worker user;
    private static String login;
    private static String password;


    public String getUserbyLogin(){
        user=userManagerBean.getUserbyLogin(login);
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        if(user.equals(null)|| !user.getPassword().equals(password)) {
            System.out.println("Zle haslo!");
            return "error";
        }
        else
            return "panel";
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
