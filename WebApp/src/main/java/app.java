import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("/")
public class app extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses();
    }
}