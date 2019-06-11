package dao;
import ejb.dto.Worker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

public class WorkerDAO {
    EntityManagerFactory factory;
    static EntityManager em;
    static List<Worker> workers =new LinkedList<Worker>();

    public WorkerDAO() {
        factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        em = factory.createEntityManager();
        getAllWorkers();
    }

    public static void getAllWorkers() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        EntityManager em = factory.createEntityManager();
        try {
            Query q = em.createQuery("FROM Worker", Worker.class);
            workers = q.getResultList();
            for (Worker w : workers)
                System.out.println(w);
        }
        catch(Exception e) {
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }

    }

    public static void deleteWorker(Long id){

        try {
            Worker foundWorker = em.find(Worker.class, id);
            DAO.delete(foundWorker,em);
        }

        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }

    }

    public static void addWorker(Worker w) {
        try {
            DAO.add(w,em);
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add data to database: " + e);
        }
    }
    public static void updateWorker(Worker worker) {
        try {
            Worker foundWorker = em.find(Worker.class, worker.getId());

            em.getTransaction().begin();
            foundWorker.setName(worker.getName());
            foundWorker.setSurname(worker.getSurname());
            em.getTransaction().commit();
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
        }
    }

    public static Worker getWorkerById(Long id){
        System.out.println("jestemtu");
        System.out.println( em.find(Worker.class,id));
    return em.find(Worker.class,id);
    }

    public Worker getWorkerbyLogin(String login) {

        Query q = em.createQuery("FROM Worker", Worker.class);
        workers = q.getResultList();

            for (Worker w : workers) {
                if (w.getLogin().equals(login))
                    return w;
            }


        return null;
    }

}