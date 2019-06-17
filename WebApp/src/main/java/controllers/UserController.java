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
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.List;
import java.util.Random;
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
    private Worker tmp_user;
    private static List<Worker> workers;
    private boolean render1=false;
    private boolean render2=false;
    private long id;
    private long meter_id;
    private String login;
    private String name;
    private String surname;
    private boolean is_Admin;
    private String pswd;



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

        userManagerBean.createUser(this.id,this.meter_id,this.login,this.name,this.surname,this.is_Admin,this.pswd);
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


    public static Logger getLOG() {
        return LOG;
    }

    public JMSHandlerManager getJmsHandler() {
        return jmsHandler;
    }

    public void setJmsHandler(JMSHandlerManager jmsHandler) {
        this.jmsHandler = jmsHandler;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMeter_id() {
        return meter_id;
    }

    public void setMeter_id(long meter_id) {
        this.meter_id = meter_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public boolean isIs_Admin() {
        return is_Admin;
    }

    public void setIs_Admin(boolean is_Admin) {
        this.is_Admin = is_Admin;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
}
