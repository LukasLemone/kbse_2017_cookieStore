package package_Cookie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "MyOrder.findAll",
            query = "SELECT o FROM MyOrder o"),
    @NamedQuery(name = "MyOrder.find", // kritisch, besser em.find()
            query = "SELECT o FROM MyOrder o WHERE o.id = :id"),
    @NamedQuery(name = "MyOrder.remove",
            query = "DELETE FROM MyOrder o WHERE o.id = :id"),
    @NamedQuery(name = "MyOrder.findOrderItem",
            query = "SELECT b FROM MyOrder s JOIN s.ordered b WHERE s.id = :id")
})
public class MyOrder implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String customer;
    
    @OneToMany(mappedBy = "myOrder", cascade = CascadeType.PERSIST)
    private List<OrderItem> ordered;
    
    public MyOrder() {
        this.ordered = new ArrayList<>();
    }
    
    public void addOrderItem(OrderItem oi){
        this.ordered.add(oi);
    }
    
    public void removeOrderItem(OrderItem oi){
        this.ordered.remove(oi);
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setOrdered(List<OrderItem> ordered) {
        this.ordered = ordered;
    }

    public String getCustomer() {
        return customer;
    }

    public List<OrderItem> getOrdered() {
        return ordered;
    }

    public int getId() {
        return id;
    }
    
    public void addOrderItemToOrdered(OrderItem oi) {
        this.ordered.add(oi);
    }
    
    public void removeOrderItemFromOrdered(OrderItem oi) {
        this.ordered.remove(oi);
    }
}
