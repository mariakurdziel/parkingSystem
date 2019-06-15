package ejb;

import dao.WorkerDAO;
import ejb.dto.Worker;
import ejb.exceptions.InvalidLoginCredentialsException;
import ejb.interfaces.UserManager;
import ejb.interfaces.remote.UserManagerRemote;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UserManagerBean implements UserManager, Serializable {

    private static String MSG;

    @Override
    public void changePassword(String login, String passwordHash){
        new WorkerDAO().changeWorkerPassword(login, passwordHash);
    }


    @Override
    public Worker getUser(Long userId) {
        System.out.println("Jestem w beanie");
        return new WorkerDAO().getWorkerById(userId);
    }

    public Worker getUserbyLogin(String login){
        return new WorkerDAO().getWorkerbyLogin(login);
    }

    @Override
    public Worker loginUser(String username) throws InvalidLoginCredentialsException {
       /*Worker w=new WorkerDAO().getWorkerbyLogin(username);

        if(w==null) {
            setMSG("No user with such a login found!");
            return null;
        }
        else if(w!=null && !w.getPassword().equals(password)) {
            setMSG("Invalid password!\n");
            return null;
        }
        else if(w!=null && w.getPassword().equals(password)){
            return w;
        }

        */
        return null;
    }

    @Override
    public void createUser(Long id,Long meter_id,String username,String name, String surname, boolean admincredentials, String hashedPassword) {
        Worker w=new Worker(id,meter_id,name,surname, username,admincredentials, hashedPassword);
        new WorkerDAO().addWorker(w);

    }

    @Override
    public void createUser(Long id,Long meter_id,String username,String name, String surname, boolean admincredentials) {
        Worker w=new Worker(id,meter_id,name,surname, username,admincredentials);
        new WorkerDAO().addWorker(w);
    }

    @Override
    public List<Worker> getListofWorkers() {
        WorkerDAO dao=new WorkerDAO();
        return dao.getWorkers();
    }

    public static String getMSG() {
        return MSG;
    }

    public static void setMSG(String MSG) {
        UserManagerBean.MSG = MSG;
    }
}
