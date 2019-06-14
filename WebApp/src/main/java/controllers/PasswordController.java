package controllers;


import ejb.dto.Worker;
import ejb.interfaces.UserManager;
import org.hibernate.query.Query;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.security.MessageDigest;

@Named("passwordController")
@SessionScoped
public class PasswordController implements Serializable {

    @EJB
    private UserManager userManagerBean;

    private static String login;
    private static String newPassword;

    public void changePassword(String userLogin, String newPassword){
        try {
            userManagerBean.changePassword(userLogin, newPassword);
        }
        catch(Exception e) {
            System.err.println("Blad przy zmienianiu hasła: " + e);
        }
    }

    public String encrypt(String password) throws Exception{
            MessageDigest md = null;
            md = MessageDigest.getInstance("MD5");
            byte[] passwordBytes = password.getBytes();
            byte[] hash = md.digest(passwordBytes);
            String passwordHash = org.jboss.security.Base64Utils.tob64(hash);
            System.out.println("skrót hasła: "+passwordHash);
            return passwordHash;
    }

    public String getLogin(){
        return login;
    }
    public void setLogin(String login){
        this.login = login;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        try {
            this.newPassword = encrypt(newPassword);
        }catch(Exception e){
            System.err.println("Bląd przy ustawianiu hasła: " + e);
        }
    }

}
