/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_Cookie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author tobias
 */
 @Dependent
public class BestellService implements Serializable{

    @Inject
    private CookiePersistence db;

    //--------------------- Bestellung --------------------------
    public void addBestellung() {
        
        Bestellung b = new Bestellung();
        this.db.addBestellung(b);
    }
    
    public void addBestellung(Bestellung b) {
        this.db.addBestellung(b);
    }

    public List<Bestellung> allBestellungen() {

        List<Bestellung> erg = new ArrayList<Bestellung>();

        for (Bestellung b : this.db.findAllBestellungen()) {
            erg.add(b);
        }

        return erg;
    }

    public void removeBestellung(int id) {
        this.db.removeBestellung(id);
    }

    public void removeBestellung(Bestellung b) {
        this.db.removeBestellung(b);
    }
    
    public Bestellung findBestellung(int id) {
        Bestellung b = this.db.findBestellung(id);
        return b;
    }
    
    //----------------------- Bestellposten -------------------------
    public void addBestellposten(int bestellungId, int cookieId, int count) {
        Bestellposten bp = new Bestellposten();
        //Bestellung b = this.db.findBestellung(bestellungId);
        bp.setCount(count);
        bp.setCookieId(cookieId);
        bp.setStatus(false);
        //bp.setBestellung(b);
        this.db.addBestellposten(bestellungId, bp);
    }
    
    public List<Bestellposten> allBestellposten(int bestellungId) {

        List<Bestellposten> erg = new ArrayList<Bestellposten>();
        Bestellung b = db.findBestellung(bestellungId);
        for (Bestellposten bp : b.getOrdered()) {
            erg.add(bp);
        }

        return erg;
    }
    
    public List<Bestellposten> allBestellposten() {

        List<Bestellposten> erg = new ArrayList<Bestellposten>();

        for (Bestellposten bp : this.db.findAllBestellposten()) {
            erg.add(bp);
        }

        return erg;
    }
    
    public Bestellposten findBestellposten(int id) {
        Bestellposten bp = this.db.findBestellposten(id);
        return bp;
    }
    
    public Bestellposten findBestellpostenByCookie(int id) {
        Bestellposten bpp = null;
        for(Bestellposten bp : this.db.findAllBestellposten()) {
            if(bp.getCookieId() == id) {
                bpp = bp;
            }
        }
        return bpp;
    }
    

    public void removeBeitrag(Bestellposten bp) {
        this.db.removeBestellposten(bp);
    }

    public void removeBeitrag(int id) {
        this.db.removeBestellposten(id);
    }

}
