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

    public void addOrder() {
        MyOrder o = new MyOrder();
        this.db.addOrder(o);
    }
    
    public void addOrder(MyOrder o) {
        this.db.addOrder(o);
    }

    public List<MyOrder> allOrders() {
        List<MyOrder> result = new ArrayList<>();
        for (MyOrder o : this.db.findAllOrders()) {
            result.add(o);
        }

        return result;
    }

    public void deleteOrder(int id) {
        this.db.removeOrder(id);
    }
    
    public MyOrder findOrder(int id) {
        MyOrder o = this.db.findOrder(id);
        return o;
    }
    
    public void deleteAllOrders() {
        for(MyOrder o : allOrders()) {
            deleteOrder(o.getId());
        }
    }
    
    //OrderItem ----------------------------------------------------------------
    public void addOrderItem(int orderId, int cookieId, int count) {
        //Ist da bereits eine Bestellung mit diesem Cookietyp?
        OrderItem oi = findOrderItemByCookie(cookieId, orderId);
        if(oi == null) {
            oi = new OrderItem();
            oi.setCount(count);
            oi.setCookieId(cookieId);
            oi.setStatus(false);
            this.db.addOrderItem(orderId, oi);
        } else {
            oi.setCount(count);
            updateOrderItem(oi);
        }
    }
    
    public List<OrderItem> allOrderItems(int bestellungId) {
        List<OrderItem> result = new ArrayList<OrderItem>();
        MyOrder b = db.findOrder(bestellungId);
        for (OrderItem bp : b.getOrdered()) {
            result.add(bp);
        }

        return result;
    }
    
    public List<OrderItem> allOrderItems() {
        List<OrderItem> result = new ArrayList<OrderItem>();
        for (OrderItem oi : this.db.findAllOrderItems()) {
            result.add(oi);
        }

        return result;
    }
    
    public OrderItem findOrderItem(int id) {
        OrderItem oi = this.db.findOrderItem(id);
        return oi;
    }
    
    public OrderItem findOrderItemByCookie(int cid, int oid) {
        OrderItem oii = null;
        MyOrder o = this.db.findOrder(oid);
        for(OrderItem oi : o.getOrdered()) {
            if(oi.getCookieId() == cid) {
                oii = oi;
            }
        }
        
        return oii;
    }
    

    public void deleteOrderItem(int id, OrderItem oi) {
        this.db.removeOrderItem(id, oi);
    }

    public void updateOrderItem(OrderItem oi) {
        this.db.merge(oi);
    }
    
    public double getOrderPrice(int id) {
        double result = 0;
        MyOrder o = db.findOrder(id);
        for(OrderItem oi : o.getOrdered()) {
            Cookie c = db.findCookie(oi.getCookieId());
            result += (c.getPrice() * oi.getCount());
        }
        
        return result;
    }
}
