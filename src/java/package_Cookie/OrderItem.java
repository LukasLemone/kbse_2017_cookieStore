package package_Cookie;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.Valid;

@Entity
@NamedQueries({
    @NamedQuery(name = "Bestellposten.findAll",
            query = "SELECT o FROM Bestellposten o"),
    @NamedQuery(name = "Bestellposten.find",
            query = "SELECT o FROM Bestellposten o WHERE o.id = :id"),
    @NamedQuery(name = "Bestellposten.remove",
            query = "DELETE FROM Bestellposten o WHERE o.id = :id")
})
class OrderItem implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @Valid
    private Order order;
    
    private int cookieId;
    private int count;
    private boolean status;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setCookieId(int cookieId) {
        this.cookieId = cookieId;
    }

    public int getCookieId() {
        return cookieId;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
}
