package ejb;

import ejb.dto.Notification;
import ejb.dto.SpotStatus;
import ejb.interfaces.ParkingServiceManager;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Stateless
@WebService
public class ParkingService implements ParkingServiceManager {

    @WebMethod
    public SpotStatus handleParkingEvent(@WebParam(name = "event")
                                                 Notification event) {
        SpotStatus  response= new SpotStatus();
       // response.setSpot(event.getSpot());
        //response.setStatus("Z");
        return response;
    }

    public ParkingService(){}
}
