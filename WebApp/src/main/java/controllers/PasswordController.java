package controllers;


import ejb.dto.Worker;
import ejb.interfaces.UserManager;
import org.hibernate.query.Query;
import org.jboss.security.auth.spi.Util;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Base64;

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
        String digest=null;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(password.getBytes("UTF-8")); //converting byte array to Hexadecimal
        // String
        StringBuilder sb = new StringBuilder(2*hash.length);
        for(byte b : hash){ sb.append(String.format("%02x", b&0xff)); }
        digest = sb.toString();

        return digest;
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
