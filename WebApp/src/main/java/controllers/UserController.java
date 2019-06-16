package controllers;
import ejb.dto.Worker;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//import ejb.interfaces.JMSMessageHandlerManager;
import ejb.interfaces.JMSHandlerManager;
import ejb.interfaces.UserManager;


@Named("User")
@SessionScoped
public class UserController implements Serializable {


    private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    @EJB
    private UserManager userManagerBean;

    @EJB
    private JMSHandlerManager jmsHandler;

    private static Worker user;
    private static Worker tmp_user;
    private static List<Worker> workers;
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

    public String createUser(){

        userManagerBean.createUser(tmp_user.getId(),tmp_user.getMeter(),tmp_user.getLogin(),tmp_user.getName(),tmp_user.getSurname(),tmp_user.isIs_admin(),tmp_user.getPasswordHash());
        getListofUsers();
        return "/login/panel.xhtml";
    }


    public String deleteUser(Long id){

        userManagerBean.deleteUser(id);
        getListofUsers();

        return "/login/panel.xhtml";

    }

    public String updateUser(){
        userManagerBean.updateUser(tmp_user);
        getListofUsers();
        return "/login/panel.xhtml";

    }
    public String redirectToForm2(){
        tmp_user=null;
        return "/login/createForm.xhtml";
    }

    public String redirectToForm1(Worker worker)
    {   tmp_user=worker;
        return "/login/form.xhtml";
    }
    public String LogOut() {

        jmsHandler.sendMsg("abc");
        String resp=jmsHandler.receiveMsg();

            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            try {
                Principal user = request.getUserPrincipal();
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

    public Worker getTmp_user() {
        return tmp_user;
    }

    public void setTmp_user(Worker tmp_user) {
        this.tmp_user = tmp_user;
    }
}
