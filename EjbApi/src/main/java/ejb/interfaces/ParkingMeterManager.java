package ejb.interfaces;

import ejb.dto.ParkingMeter;
import ejb.dto.Ticket;
import ejb.dto.Worker;
import java.util.List;

public interface ParkingMeterManager {

    ParkingMeter getParkingMeterbyId(Worker w);
    List<Ticket> getParkingMeterTickets(long id);
}
