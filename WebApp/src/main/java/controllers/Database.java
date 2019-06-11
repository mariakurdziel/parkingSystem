package controllers;

import ejb.dto.ParkingMeter;
import ejb.dto.Worker;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean(name="database")
@RequestScoped
public class Database {

    public void createTable() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        EntityManager em = factory.createEntityManager();
        try {
            Worker w1=new Worker((long)2246,(long)223,"Albert","Camus","albert123","xhtml1",true);
            Worker w2=new Worker((long)2247,(long)222,"Monika","Szwaja","monia7","xhtml2",false);
            Worker w3=new Worker((long)2248,(long)222,"Teodor","Kowalski","teodorr","xhtml3",false);
            Worker w4=new Worker((long)2249,(long)220,"Milan","Nowak","nowak14","xhtml4",true);
            Worker w5=new Worker((long)2250,(long)222,"Maria","Achmatowa","achmat","xhtml5",false);
            Worker w6=new Worker((long)2251,(long)224,"Ola","Makota","makko","xhtml6",false);
            Worker w7=new Worker((long)2252,(long)225,"Alicja","Machomika","chomik1","xhtml7",true);
            Worker w8=new Worker((long)2253,(long)225,"Zenon","Lipiec","latowiec","xhtml8",false);
            Worker w9=new Worker((long)2345,(long)223,"Fiodor","Dostojewski","fiodo999","xhtml9",false);
            Worker w10=new Worker((long)2354,(long)224,"Damian","Mienny","imiennik1","xhtml10",false);

            ParkingMeter p1=new ParkingMeter((long)220,2.5,"Powstanców 18");
            ParkingMeter p2=new ParkingMeter((long)222,2.0,"Powstanców 4");
            ParkingMeter p3=new ParkingMeter((long)223,2.5,"Zlotopolska 2");
            ParkingMeter p4=new ParkingMeter((long)224,2.5,"Ryjowek 5");
            ParkingMeter p5=new ParkingMeter((long)225,2.5,"Ryjowek 18");

            em.getTransaction().begin();
            w1=em.merge(w1);
            w2=em.merge(w2);
            w3=em.merge(w3);
            w4=em.merge(w4);
            w5=em.merge(w5);
            w6=em.merge(w6);
            w7=em.merge(w7);
            w8=em.merge(w8);
            w9=em.merge(w9);
            w10=em.merge(w10);
            p1=em.merge(p1);
            p2=em.merge(p2);
            p3=em.merge(p3);
            p4=em.merge(p4);
            p5=em.merge(p5);

            em.getTransaction().commit();
            System.out.println("Zapisano w bazie: " + w1);
            System.out.println("Zapisano w bazie: " + w2);
        }
        catch(Exception e) {
            System.err.println("Blad przy dodawaniu rekordu: " + e);
        }
    }
}
