package ejb.interfaces;
import ejb.dto.Worker;
import ejb.exceptions.InvalidLoginCredentialsException;

public interface UserManager {
    Worker getUserbyLogin(String login);
    Worker getUser(Long userId);
    Worker loginUser(String username, String password) throws InvalidLoginCredentialsException;
    void createUser(Long id,Long meter_id,String username, String password, String name, String surname, boolean admincredentials);
}
