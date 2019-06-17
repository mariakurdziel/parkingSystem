package ejb;

import dao.ParkingMeterDAO;
import dao.ParkingSpotDAO;
import dao.TicketDAO;
import ejb.dto.ParkingMeter;
import ejb.dto.ParkingSpot;
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

    @Override
    public List<ParkingSpot> getParkingSpots(long id) {
        ParkingSpotDAO.getAllParkingSpots();
        List <ParkingSpot> tmp=ParkingSpotDAO.getSpots();
        List<ParkingSpot> spots=new LinkedList<ParkingSpot>();
        for(ParkingSpot s: tmp){
            if(s.getPark_id()==id)
                spots.add(s);
        }
        return spots;
    }
}
