package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ejb.dto.ParkingSpot;

import java.util.LinkedList;
import java.util.List;

import static ejb.dto.ParkingSpot.*;

public class ParkingSpotDAO {

    EntityManagerFactory factory;
    static EntityManager em;
    static List<ParkingSpot> spots=new LinkedList<ParkingSpot>();

    public ParkingSpotDAO() {
        factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        em = factory.createEntityManager();
        getAllParkingSpots();
    }

    public static void getAllParkingSpots() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        EntityManager em = factory.createEntityManager();
        try {
            Query q = em.createQuery("FROM ParkingSpot ", ParkingSpot.class);
            spots = q.getResultList();
            for (ParkingSpot s : spots)
                System.out.println(s);
        }
        catch(Exception e) {
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }

    }

    public static void deleteParkingSpot(Long id){

        try {
            ParkingSpot foundParkingSpot = em.find(ParkingSpot.class, id);
            DAO.delete(foundParkingSpot,em);
        }

        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }

    }

    public static void addParkingSpot(ParkingSpot p) {
        try {
            DAO.add(p,em);
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add data to database: " + e);
        }
    }
    public static void updateParkingSpot(ParkingSpot spot) {
        try {
            ParkingSpot foundParkingSpot = em.find(ParkingSpot.class, spot.getId());

            em.getTransaction().begin();
            foundParkingSpot.setReserved(spot.isReserved());
            em.getTransaction().commit();
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
        }
    }

    public static ParkingSpot getParkingSpotById(Long id){
        return em.find(ParkingSpot.class,id);
    }
}
