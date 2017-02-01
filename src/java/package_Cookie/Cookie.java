package packageForCookieStuff;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cookie implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double prize;    
    private int count;

    public Cookie() {
    }

    public Cookie(int id, String name, double prize, int count) {
        this.id = id;
        this.name = name;
        this.prize = prize;
        this.count = count;
    }

    // Getter
    public int getId() {
        return id;
    } 
    public String getName() {
        return name;
    }
    public double getPrize() {
        return prize;
    }
    public int getCount() {
        return count;
    }
    
    // Setter
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrize(double prize) {
        this.prize = prize;
    }
    public void setCount(int count) {
        this.count = count;
    }   
}
