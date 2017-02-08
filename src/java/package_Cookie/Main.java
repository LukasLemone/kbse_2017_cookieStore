/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_Cookie;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tobias
 */
public class Main {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Kbse_2017_Luto_PU");
    private EntityManager em = emf.createEntityManager();
    //private CookiePersistence cp = new CookiePersistence();    
    
    
    public static void main(String[] s) {
        new Main().init();
    }
    
    public void init() {
        Cookie c = new Cookie();
        c.setName("zimtstern");
        c.setCount(10);
        c.setPrize(2.9);
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        }catch(Exception e) {
            System.out.println("Fehler");
        }
        
        
        
        //this.cp.addCookie(c);
    }
}
