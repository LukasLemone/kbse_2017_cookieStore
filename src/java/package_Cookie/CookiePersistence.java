package package_Cookie;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class CookiePersistence {
    
    @Inject
    private EntityManager em;
    
    //basic persistence-stuff
    public void persist(Object object) {
        em.persist(object);
    }
    
    public Object merge(Object object) {
        return em.merge(object);
    }
    
    public void remove(Object object) {
        em.remove(object);
    }
    
    public void flush() {
        em.flush();
    }
    
    public void refresh(Object object) {
        em.refresh(object);
    }
    
    //--------------cookie-stuff-------------------------------------
    
    public Cookie findCookie(int id) {
        return this.em.find(Cookie.class, id);
    }
    
    public List<Cookie> findAllCookies() {
        //muss das alles so? findAllCookies wird drei mal aufgerufen
        System.out.println("Aufruf findAllCookies");
        return em.createNamedQuery("Cookie.findAll", Cookie.class).getResultList();
    }
    
    public void removeCookie(int id) {
        
        System.out.println("Aufruf removeCookie");
        try {
            Cookie c = findCookie(id);
            merge(c);
            remove(c);
        } catch(Exception e) {
            System.out.println("Exception remove");
        }
        
    }
    
    public void addCookie(Cookie c) {
        System.out.println("Aufruf addCookie");
        try {
            this.persist(c);
        } catch(Exception e) {
            System.out.println("Exception persist");
        }
    }
    
    //--------------Bestellung-Stuff-------------------
        
    public Bestellung findBestellung(int id) {
        return this.em.find(Bestellung.class, id);
    }
    
    public List<Bestellung> findAllBestellungen() {
        System.out.println("Aufruf findAllBestellungen");
        return em.createNamedQuery("Bestellung.findAll", Bestellung.class).getResultList();
    }
    
    public void removeBestellung(int id) {
        
        System.out.println("Aufruf removeBestellung");
        try {
            Bestellung b = findBestellung(id);
            merge(b);
            removeBestellung(b);
        } catch(Exception e) {
            System.out.println("Exception remove");
        }
        
    }
    
    public void removeBestellung(Bestellung b) {
        for(Bestellposten bp : b.getOrdered()) {
            remove(bp);
        }
        remove(b);
    }
    
    public void addBestellung(Bestellung b) {
        System.out.println("Aufruf addBestellung");
        try {
            
            this.persist(b);
        } catch(Exception e) {
            System.out.println("Exception persist");
        }
    }
    
    //--------------Bestellposten-Stuff-------------------
    
    public void addBestellposten(int id, Bestellposten bp) {
        Bestellung b = this.findBestellung(id);
        try {
           b.addBestellposten(bp);
           bp.setBestellung(b);
           this.merge(b);
           this.persist(bp);
        } catch (Exception e) {
            System.out.println("Exception persist");
        }
    }
    
    public Bestellposten findBestellposten(int id) {
        return this.em.find(Bestellposten.class, id);
    }
    
    public List<Bestellposten> findAllBestellposten() {
        return em.createNamedQuery("Bestellposten.findAll",Bestellposten.class).getResultList();
    }
    
    public void removeBestellposten(int id) {
        Bestellposten bp = em.find(Bestellposten.class, id);
        removeBestellposten(bp);
    }
    
    public void removeBestellposten(Bestellposten bp) {
        try {
            this.merge(bp);
            remove(bp);
        }catch(Exception e) {
            System.out.println("Exception persist");
        }
        
    }
}
