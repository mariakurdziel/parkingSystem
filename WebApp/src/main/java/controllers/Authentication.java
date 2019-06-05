package controllers;

import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

import javax.security.auth.login.LoginException;
import java.security.acl.Group;

public class Authentication extends UsernamePasswordLoginModule {

    @Override
    protected String getUsersPassword()throws LoginException {
        return "";
    }

    @Override
    protected Group[] getRoleSets() throws LoginException{

        return new Group[0];
    }
}
