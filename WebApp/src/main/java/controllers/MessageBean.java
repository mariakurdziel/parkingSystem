package controllers;

import dao.NotificationDAO;
import dao.ParkingSpotDAO;
import dao.TicketDAO;
import ejb.dto.Notification;
import ejb.dto.ParkingSpot;
import ejb.dto.SpotStatus;
import ejb.dto.Ticket;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.inject.spi.Producer;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.SchemaOutputResolver;

import java.sql.Time;
import java.text.MessageFormat;
import java.util.Date;


@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "java:jboss/exported/jms/queue/Project"
        ),
        @ActivationConfigProperty(
                propertyName = "messageSelector",
                propertyValue = "Operation = 'Request'"
        )

})
@Named("msg")
public class MessageBean implements MessageListener {


    @Inject
    @JMSConnectionFactory("java:/jms/MyXaConnectionFactory")
    private JMSContext mdc;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private SpotStatus processNotification(Notification notification){
        SpotStatus stat=new SpotStatus();
        ParkingSpot spot;
        stat.setSpot(notification.getSpot_id());
        //stat.setStatus(not.getType());
        try{
            new NotificationDAO().addNotification(notification);
            spot=new ParkingSpotDAO().getParkingSpotById(notification.getSpot_id());
            if(notification.getType().equals("W")){  //wjechał
                if(spot.isReserved()) {
                    stat.setError(MessageFormat.format("Miejsce {0} jest już zajete", notification.getSpot_id()));
                    stat.setReserved(spot.isReserved());
                    stat.setPaid(spot.isIs_paid());
                    return stat;
                } else {
                    spot.setStart_time(notification.getStart());
                    spot.setReserved(true);

                    new ParkingSpotDAO().updateParkingSpot(spot);
                    stat.setReserved(true);
                }
            }
            else if(notification.getType().equals("Z")){  //zwolniony
                if(!spot.isReserved()) {
                    stat.setError(MessageFormat.format("Miejsce {0} jest już wolne", notification.getSpot_id()));
                    stat.setReserved(spot.isReserved());
                    stat.setPaid(spot.isIs_paid());
                    return stat;
                } else {
                    spot.setReserved(false);
                    spot.setIs_paid(false);
                    spot.setDuration(null);
                    spot.setStart_time(null);
                    new ParkingSpotDAO().updateParkingSpot(spot);
                    stat.setReserved(true);
                }
            }
            else if(notification.getType().equals("O")){
                if(!spot.isReserved() ||spot.isIs_paid()) {
                    stat.setError(MessageFormat.format("Nie można opłacić miejsca {0}", notification.getSpot_id()));
                    stat.setReserved(spot.isReserved());
                    stat.setPaid(spot.isIs_paid());
                    return stat;
                } else {
                    long reg=new Date().getTime();
                    String ident=new Long(reg).toString();
                    Ticket t=new Ticket(notification.getId(),notification.getSpot_id(),spot.getPark_id(),2.0,ident, new Date(),null);
                    new TicketDAO().addTicket(t);
                    spot.setReserved(true);
                    spot.setIs_paid(true);
                    spot.setDuration(notification.getDuration());
                    new ParkingSpotDAO().updateParkingSpot(spot);
                    stat.setReserved(true);
                }

            } //opłacony
        } catch (Exception e) {
            stat.setError(e.getMessage());
        }
        return stat;
    }

    @Override
    public void onMessage(Message message) {

        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage objMessage = (ObjectMessage) message;
                Notification not=(Notification)objMessage.getObject();

                SpotStatus stat=processNotification(not);

                Destination dest= null;
                String id=message.getStringProperty("Id");
                dest = message.getJMSDestination();
                JMSProducer prod=mdc.createProducer();
                ObjectMessage resp=mdc.createObjectMessage(stat);
                resp.setStringProperty("Source", id);
                resp.setStringProperty("Operation", "Response");
                prod.send(dest, resp);
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }
}