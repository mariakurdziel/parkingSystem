package dao;

import ejb.dto.Notification;
import ejb.dto.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class NotificationDAO {

    EntityManagerFactory factory;
    static EntityManager em;


    public NotificationDAO() {
        factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        em = factory.createEntityManager();
    }

    public void addNotification(Notification n) {
        try {
            DAO.add(n,em);
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add data to database: " + e);
        }
    }

}
