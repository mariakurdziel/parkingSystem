package ejb;

import dao.ParkingMeterDAO;
import dao.TicketDAO;
import ejb.dto.ParkingMeter;
import ejb.dto.Ticket;
import ejb.dto.Worker;
import ejb.interfaces.ParkingMeterManager;

import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class ParkingMeterManagerBean implements ParkingMeterManager, Serializable {

    @Override
    public ParkingMeter getParkingMeterbyId(Worker w) {
        return new ParkingMeterDAO().getParkingMeterById(w.getMeter());
    }

    @Override
    public List<Ticket> getParkingMeterTickets(long id) {
        TicketDAO.getAllUsers();
        List <Ticket> tmp=TicketDAO.getTickets();
         List<Ticket> tickets=new LinkedList<Ticket>();
        for(Ticket t: tmp){
            if(t.getId_parkingu()==id)
                tickets.add(t);
        }
        return tickets;
    }
}
