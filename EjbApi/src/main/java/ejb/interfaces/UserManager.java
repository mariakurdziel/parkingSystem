package ejb.interfaces;
import ejb.dto.Worker;
import ejb.exceptions.InvalidLoginCredentialsException;

import java.util.List;

import java.util.List;

public interface UserManager {
    Worker getUserbyLogin(String login);
    Worker getUser(Long userId);
    void changePassword(String login, String passwordHash);
    Worker loginUser(String username) throws InvalidLoginCredentialsException;
    void createUser(Long id,Long meter_id,String username, String name, String surname, boolean admincredentials, String hashedPassword);
    void createUser(Long id,Long meter_id,String username, String name, String surname, boolean admincredentials);
    List<Worker> getListofWorkers();
}
