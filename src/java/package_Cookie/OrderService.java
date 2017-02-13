package package_Cookie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class OrderService implements Serializable {

    @Inject
    private CookiePersistence db;

    //Bestellung
    public void addOrder() {
        Order o = new Order();
        this.db.addBestellung(o);
    }
    
    public void addOrder(Order o) {
        this.db.addBestellung(o);
    }

    public List<Order> everyOrder() {
        List<Order> erg = new ArrayList<Order>();

        for (Order o : this.db.findAllBestellungen()) {
            erg.add(o);
        }

        return erg;
    }

    public void deleteOrder(int id) {
        this.db.removeBestellung(id);
    }
    
    public Order findOrder(int id) {
        Order o = this.db.findBestellung(id);
        return o;
    }
    
    public void deleteEveryOrder() {
        for(Order o : everyOrder()) {
            deleteOrder(o.getId());
        }
    }
    
    //Bestellposten
    public void addOrderItem(int orderId, int cookieId, int count) {
        //Is there already an order with this cookietype?
        OrderItem oi = findOrderItemByCookie(cookieId, orderId);
        if(oi == null) {
            oi = new OrderItem();
            oi.setCount(count);
            oi.setCookieId(cookieId);
            oi.setStatus(false);
            this.db.addBestellposten(orderId, oi);
        } else {
            oi.setCount(count);
            updateOrderItem(oi);
        }
    }
    
    public List<OrderItem> allOrderItems(int bestellungId) {
        List<OrderItem> erg = new ArrayList<OrderItem>();
        Order b = db.findBestellung(bestellungId);
        for (OrderItem bp : b.getOrdered()) {
            erg.add(bp);
        }

        return erg;
    }
    
    public List<OrderItem> allOrderItems() {
        List<OrderItem> erg = new ArrayList<OrderItem>();

        for (OrderItem oi : this.db.findAllBestellposten()) {
            erg.add(oi);
        }

        return erg;
    }
    
    public OrderItem findOrderItem(int id) {
        OrderItem oi = this.db.findBestellposten(id);
        return oi;
    }
    
    public OrderItem findOrderItemByCookie(int cid, int oid) {
        OrderItem _oi = null;
        Order o = this.db.findBestellung(oid);
        for(OrderItem oi : o.getOrdered()) {
            if(oi.getCookieId() == cid) {
                _oi = oi;
            }
        }
        return _oi;
    }
    

    public void deleteOrderItem(int id,OrderItem oi) {
        this.db.removeBestellposten(id,oi);
    }

    public void updateOrderItem(OrderItem oi) {
        this.db.merge(oi);
    }
}
