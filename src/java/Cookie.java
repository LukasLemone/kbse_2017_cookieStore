
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lukas
 */
@Entity
public class Cookie implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double prize;    
    private int count;

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
