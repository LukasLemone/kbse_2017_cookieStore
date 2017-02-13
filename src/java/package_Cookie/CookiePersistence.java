package package_Cookie;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class CookiePersistence {
    
    @Inject
    private EntityManager em;
    
    //Persistence Funktionen
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
    
    //Cookie Funktionen
    public Cookie findCookie(int id) {
        return this.em.find(Cookie.class, id);
    }
    
    public List<Cookie> findAllCookies() {
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
            System.out.println("Exception persist Cookie");
        }
    }
    
    //Bestellung Funktionen
    public Order findBestellung(int id) {
        return this.em.find(Order.class, id);
    }
    
    public List<Order> findAllBestellungen() {
        System.out.println("Aufruf findAllBestellungen");
        return em.createNamedQuery("Bestellung.findAll", Order.class).getResultList();
    }
    
    public void removeBestellung(int id) {
        Order b = findBestellung(id);
        System.out.println("Aufruf remove Bestellung");
        
        try {
            for(OrderItem bp : b.getOrdered()) {
                removeBestellposten(id, bp);
            }
            remove(b);
            System.out.println("Erfolg remove Bestellung");
        } catch(Exception e) {
            System.out.println("Exception remove Bestellung "+b.getId());
        }
    }
    
    public void addBestellung(Order b) {
        System.out.println("Aufruf addBestellung");
        
        try {
            this.persist(b);
        } catch(Exception e) {
            System.out.println("Exception persist Bestellung");
        }
    }
    
    //Bestellposten Funktionen
    public void addBestellposten(int id, OrderItem bp) {
        Order b = this.findBestellung(id);
        
        try {
           b.addOrderItem(bp);
           bp.setOrder(b);
           this.persist(bp);
           System.out.println("Erfolg persist Bestellposten");
        } catch (Exception e) {
            System.out.println("Exception persist Bestellposten");
        }
    }
    
    public OrderItem findBestellposten(int id) {
        return this.em.find(OrderItem.class, id);
    }
    
    public List<OrderItem> findAllBestellposten() {
        return em.createNamedQuery("Bestellposten.findAll",OrderItem.class).getResultList();
    }
    
    public void removeBestellposten(int id, OrderItem bp) {
        System.out.println("Aufruf remove Bestellposten");
        
        Order b = this.findBestellung(id);
        try {
            this.merge(bp);
            remove(bp);
            System.out.println("erfolg remove Bestellposten "+bp.getId());
        } catch(Exception e) {
            System.out.println("Exception remomve Bestellposten "+bp.getId());
        }
    }
}
