package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ejb.dto.ParkingMeter;

import java.util.LinkedList;
import java.util.List;

public class ParkingMeterDAO {
    EntityManagerFactory factory;
    static EntityManager em;
    static List<ParkingMeter> meters=new LinkedList<ParkingMeter>();

    public ParkingMeterDAO() {
        factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        em = factory.createEntityManager();
        getAllParkingMeters();
    }

    public static void getAllParkingMeters() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        EntityManager em = factory.createEntityManager();
        try {
            Query q = em.createQuery("FROM ParkingMeter", ParkingMeter.class);
            meters = q.getResultList();
            for (ParkingMeter m : meters)
                System.out.println(m);
        }
        catch(Exception e) {
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }

    }

    public static void deleteParkingMeter(Long id){

        try {
            ParkingMeter foundParkingMeter = em.find(ParkingMeter.class, id);
            DAO.delete(foundParkingMeter,em);
        }

        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }

    }

    public static void addParkingMeter(ParkingMeter m) {
        try {
            DAO.add(m,em);
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add data to database: " + e);
        }
    }
    public static void updateParkingMeter(ParkingMeter m) {
        try {
            ParkingMeter foundParkingMeter = em.find(ParkingMeter.class, m.getId());

            em.getTransaction().begin();
            foundParkingMeter.setPrice(m.getPrice());
            foundParkingMeter.setLocation(m.getLocation());
            em.getTransaction().commit();
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
        }
    }

    public static ParkingMeter getParkingMeterById(Long id){
    return em.find(ParkingMeter.class,id);
}
}
