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
    
    //--------------cookie-persistence-stuff-------------------------------------
    
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
}
