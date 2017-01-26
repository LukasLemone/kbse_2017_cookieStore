
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
public class CookieManager implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Cookie cookies[];
    
}
