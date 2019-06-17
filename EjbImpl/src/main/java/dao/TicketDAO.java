package dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ejb.dto.Ticket;
import java.util.LinkedList;
import java.util.List;


public class TicketDAO {
    EntityManagerFactory factory;
    static EntityManager em;
    static List<Ticket> tickets=new LinkedList<Ticket>();

    public TicketDAO() {
        factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        em = factory.createEntityManager();
        getAllUsers();
    }

    public static void getAllUsers() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        EntityManager em = factory.createEntityManager();
        try {
            Query q = em.createQuery("FROM Ticket", Ticket.class);
            tickets = q.getResultList();
            for (Ticket t : tickets)
                System.out.println(t);
        }
        catch(Exception e) {
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }

    }

    public static void deleteTicket(Long id){

        try {
            Ticket foundTicket = em.find(Ticket.class, id);
            DAO.delete(foundTicket,em);
        }

        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }

    }

    public void addTicket(Ticket t) {
        try {
            DAO.add(t,em);
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add data to database: " + e);
        }
    }
    public static void updateTicket(Ticket ticket) {
        try {
            Ticket foundTicket = em.find(Ticket.class, ticket.getId());

            em.getTransaction().begin();
            foundTicket.setPrice(ticket.getPrice());
            foundTicket.setStart_time(ticket.getStart_time());
            foundTicket.setDuration(ticket.getDuration());
            foundTicket.setRegistration_number(ticket.getRegistration_number());
            foundTicket.setSpot_id(ticket.getSpot_id());
            foundTicket.setId_parkingu(ticket.getId_parkingu());
            em.getTransaction().commit();
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
        }
    }

    public static Ticket getTicketById(Long id){
        return em.find(Ticket.class,id);
    }

    public static List<Ticket> getTickets() {
        return tickets;
    }

    public static void setTickets(List<Ticket> tickets) {
        TicketDAO.tickets = tickets;
    }
}