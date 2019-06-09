import javax.ejb.*;
import javax.ws.rs.*;
@Path("name")
@Stateless
public class rest {
    @EJB
    private restSingleton Singleton;
    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<h2>Hello "+Singleton.getName()+"</h2>";
    }
    @PUT
    @Consumes("text/plain")
    public void put(String content) {
        Singleton.setName(content);
    }}
