package controllers;
import ejb.dto.Worker;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("User")
@SessionScoped
public class UserController implements Serializable {

    public Worker getUserbyLogin(){
        return null;
    }
}
