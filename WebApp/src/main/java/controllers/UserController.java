package controllers;
import ejb.dto.Ticket;
import ejb.dto.Worker;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import ejb.interfaces.UserManager;
import ejb.interfaces.remote.UserManagerRemote;


@Named("User")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private UserManager userManagerBean;
    private static Worker user;
    private List<Worker> workers;
    private boolean render1=false;
    private boolean render2=false;


    @PostConstruct
    public void init(){
        if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = userManagerBean.getUserbyLogin(principal.getName());
            }
        }

        if(user.isIs_admin()) {
            render1 = false;
            render2 = true;
        }
        else
        {
            render1=true;
            render2=false;
        }

        getListofUsers();

    }

    public String LogOut() {
        FacesContext fc=FacesContext.getCurrentInstance();
        HttpServletRequest request= (HttpServletRequest)fc.getExternalContext().getRequest();
        try {
            Principal user=request.getUserPrincipal();
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
         return "/login/panel.xhtml";
    }

    public void getListofUsers(){

        workers=userManagerBean.getListofWorkers();

    }

    public Worker getUser() {
        return user;
    }

    public void setUser(Worker user) {
        this.user = user;
    }

    public UserManager getUserManagerBean() {
        return userManagerBean;
    }

    public void setUserManagerBean(UserManager userManagerBean) {
        this.userManagerBean = userManagerBean;
    }

    public boolean isRender1() {
        return render1;
    }

    public void setRender1(boolean render1) {
        this.render1 = render1;
    }

    public boolean isRender2() {
        return render2;
    }

    public void setRender2(boolean render2) {
        this.render2 = render2;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
