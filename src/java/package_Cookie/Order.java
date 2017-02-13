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
    @NamedQuery(name = "Bestellung.findAll",
            query = "SELECT o FROM Bestellung o"),
    @NamedQuery(name = "Bestellung.find", // kritisch, besser em.find()
            query = "SELECT o FROM Bestellung o WHERE o.id = :id"),
    @NamedQuery(name = "Bestellung.remove",
            query = "DELETE FROM Bestellung o WHERE o.id = :id"),
    @NamedQuery(name = "Bestellung.findBestellposten",
            query = "SELECT b FROM Bestellung s JOIN s.ordered b WHERE s.id = :id")
})
public class Order implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String customer;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> ordered;
    
    public Order() {
        this.ordered = new ArrayList<OrderItem>();
    }
    
    public void addOrderItem(OrderItem oi){
        this.ordered.add(oi);
    }
    
    public void removeOrderItem(OrderItem bp){
        this.ordered.remove(bp);
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
