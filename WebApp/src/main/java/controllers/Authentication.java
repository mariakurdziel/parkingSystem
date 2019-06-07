package controllers;

import ejb.dto.Worker;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

import javax.security.auth.login.LoginException;
import java.security.acl.Group;
import java.util.logging.Logger;

public class Authentication extends UsernamePasswordLoginModule {

    private String role;

    @Override
    protected String getUsersPassword()throws LoginException {
        String username=getUsername();
        LOG.info("rozpoznawanie użytkownika..."+username);
        Worker w=new UserController().getUserbyLogin(username);
        if(w.isIs_admin()==true) {
            role = "admin";
            return w.getPassword();
        }
        else if(w.isIs_admin()==false){
            role="user";
            return w.getPassword();
        }
        else
            return null;
    }

    private static Logger LOG =Logger.getLogger(Authentication.class.getName());

    @Override
    protected Group[] getRoleSets() throws LoginException{

        Group[] groups = {new SimpleGroup("roles")};
        SimplePrincipal r = new SimplePrincipal(role);
        groups[0].addMember(r);

        return groups;
    }
}
