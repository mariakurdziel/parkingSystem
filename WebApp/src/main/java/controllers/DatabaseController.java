package controllers;

import ejb.dto.ParkingMeter;
import ejb.dto.ParkingSpot;
import ejb.dto.Ticket;
import ejb.dto.Worker;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Time;
import java.util.Date;

@ManagedBean(name="database")
@RequestScoped
public class DatabaseController {

    public void createTable() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        EntityManager em = factory.createEntityManager();
        try {
           /* Worker w1=new Worker((long)2246,(long)223,"Albert","Camus","albert123",true, "f1863f23897ea9c14d9606d667e84685");
            Worker w2=new Worker((long)2247,(long)222,"Monika","Szwaja","monia7",false, "f1863f23897ea9c14d9606d667e84685");
            Worker w3=new Worker((long)2248,(long)222,"Teodor","Kowalski","teodorr",false, "f1863f23897ea9c14d9606d667e84685");
            Worker w4=new Worker((long)2249,(long)220,"Milan","Nowak","nowak14",true, "f1863f23897ea9c14d9606d667e84685");
            Worker w5=new Worker((long)2250,(long)222,"Maria","Achmatowa","achmat",false, "f1863f23897ea9c14d9606d667e84685");
            Worker w6=new Worker((long)2251,(long)224,"Ola","Makota","makko",false, "f1863f23897ea9c14d9606d667e84685");
            Worker w7=new Worker((long)2252,(long)225,"Alicja","Machomika","chomik1",true, "f1863f23897ea9c14d9606d667e84685");
            Worker w8=new Worker((long)2253,(long)225,"Zenon","Lipiec","latowiec",false, "f1863f23897ea9c14d9606d667e84685");
            Worker w9=new Worker((long)2345,(long)223,"Fiodor","Dostojewski","fiodo999",false, "f1863f23897ea9c14d9606d667e84685");
            Worker w10=new Worker((long)2354,(long)224,"Damian","Mienny","imiennik1",false, "f1863f23897ea9c14d9606d667e84685");

            ParkingMeter p1=new ParkingMeter((long)220,2.5,"Powstancow 18");
            ParkingMeter p2=new ParkingMeter((long)222,2.0,"Powstancow 4");
            ParkingMeter p3=new ParkingMeter((long)223,2.5,"Zlotopolska 2");
            ParkingMeter p4=new ParkingMeter((long)224,2.5,"Ryjowek 5");
            ParkingMeter p5=new ParkingMeter((long)225,2.5,"Ryjowek 18");

            ParkingSpot s1=new ParkingSpot((long)2333,(long)222,false);
            ParkingSpot s2=new ParkingSpot((long)2387,(long)224,false);
            ParkingSpot s3=new ParkingSpot((long)2388,(long)224,false);
            ParkingSpot s4=new ParkingSpot((long)2389,(long)224,false);
            ParkingSpot s5=new ParkingSpot((long)2444,(long)223,false);
            ParkingSpot s6=new ParkingSpot((long)2445,(long)223,false);
            ParkingSpot s7 = new ParkingSpot((long)2446,(long)220,false);
            ParkingSpot s8=new ParkingSpot((long)2447,(long)220,false);
            ParkingSpot s9=new ParkingSpot((long)2448,(long)225,false);
            ParkingSpot s10=new ParkingSpot((long)2449,(long)225,false);

           */

            Ticket t1=new Ticket((long)1,(long)2444,(long)223,2.5,"POL 22 33",new Date(),new Time(1,15,0));
            Ticket t2=new Ticket((long)2,(long)2445,(long)223,2.5,"POL 28 34",new Date(),new Time(0,40,0));
            Ticket t3=new Ticket((long)3,(long)2333,(long)222,2.0,"POL 22 33",new Date(),new Time(2,0,0));
            Ticket t4=new Ticket((long)4,(long)2333,(long)222,2.0,"POL 26 38",new Date(),new Time(0,15,0));
            Ticket t5=new Ticket((long)5,(long)2446,(long)220,2.5,"POL 29 37",new Date(),new Time(0,2,0));
            Ticket t6=new Ticket((long)6,(long)2447,(long) 220,2.5,"POL 22 40",new Date(),new Time(0,13,0));
            Ticket t8=new Ticket((long)8,(long)2448,(long)225,2.5,"POL 22 88",new Date(),new Time(17,0,0));
            Ticket t9=new Ticket((long)9,(long)2388,(long)224,2.5,"POL 22 69",new Date(),new Time(0,14,0));
            Ticket t10=new Ticket((long)10,(long)2445,(long)223,2.5,"POL 11 33",new Date(),new Time(1,0,0));
            em.getTransaction().begin();
           /* w1=em.merge(w1);
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

            s1=em.merge(s1);
            s2=em.merge(s2);
            s3=em.merge(s3);
            s4=em.merge(s4);
            s5=em.merge(s5);
            s6=em.merge(s6);
            s7=em.merge(s7);
            s8=em.merge(s8);
            s9=em.merge(s9);
            s10=em.merge(s10);*/

            t1=em.merge(t1);
            t2=em.merge(t2);
            t3=em.merge(t3);
            t4=em.merge(t4);
            t5=em.merge(t5);
            t6=em.merge(t6);
            t8=em.merge(t8);
            t9=em.merge(t9);
            t10=em.merge(t10);

            em.getTransaction().commit();
            System.out.println("Zapisano w bazie: " + t1);
            System.out.println("Zapisano w bazie: " + t2);
        }
        catch(Exception e) {
            System.err.println("Blad przy dodawaniu rekordu: " + e);
        }
    }
}
