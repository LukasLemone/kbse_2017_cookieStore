package package_Cookie;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class CookiePersistence {
    @Inject
    private EntityManager em;
    
    //Persistence Funktionen ---------------------------------------------------
    public void persist(Object object) {
        em.persist(object);
    }
    
    public Object merge(Object object) {
        return em.merge(object);
    }
    
    public void remove(Object object) {
        em.remove(object);
    }
    //Wird nicht genutzt!
    public void flush() {
        em.flush();
    }
    //Wird nicht genutzt!
    public void refresh(Object object) {
        em.refresh(object);
    }
    
    //Cookie Funktionen --------------------------------------------------------
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
    
    //MyOrder Funktionen -------------------------------------------------------
    public MyOrder findOrder(int id) {
        return this.em.find(MyOrder.class, id);
    }
    
    public List<MyOrder> findAllOrders() {
        System.out.println("Aufruf findAllOrders");
        return em.createNamedQuery("MyOrder.findAll", MyOrder.class).getResultList();
    }
    
    public void removeOrder(int id) {
        System.out.println("Aufruf removeOrder");
        MyOrder o = findOrder(id);        
        try {
            System.out.println("before removeOI");
            for(OrderItem oi : o.getOrdered()) {
                removeOrderItem(id, oi);
            }
            System.out.println("after removeOI");
            remove(o);
            System.out.println("Erfolg remove Order");
        } catch(Exception e) {
            System.out.println("Exception remove Order "+o.getId());
        }
    }
    
    public void addOrder(MyOrder o) {
        System.out.println("Aufruf addOrder");
        
        try {
            this.persist(o);
        } catch(Exception e) {
            System.out.println("Exception persist Order");
        }
    }
    
    //OrderItem Funktionen -----------------------------------------------------
    public void addOrderItem(int id, OrderItem oi) {
        MyOrder o = this.findOrder(id);
        
        try {
           o.addOrderItem(oi);
           oi.setMyOrder(o);
           this.persist(oi);
           System.out.println("Erfolg persist OrderItem");
        } catch (Exception e) {
            System.out.println("Exception persist OrderItem");
        }
    }
    
    public OrderItem findOrderItem(int id) {
        return this.em.find(OrderItem.class, id);
    }
    
    public List<OrderItem> findAllOrderItems() {
        return em.createNamedQuery("OrderItem.findAll",OrderItem.class).getResultList();
    }
    
    public void removeOrderItem(int id, OrderItem oi) {
        System.out.println("Aufruf remove OrderItem");
        
        MyOrder o = this.findOrder(id);
        try {
            
            
            OrderItem toBeRemoved = em.merge(oi);
            em.remove(toBeRemoved);
            
            System.out.println("erfolg remove OrderItem "+oi.getId());
        } catch(Exception e) {
            System.out.println("Exception remomve OrderItem "+oi.getId());
            e.printStackTrace();
        }
    }
}
