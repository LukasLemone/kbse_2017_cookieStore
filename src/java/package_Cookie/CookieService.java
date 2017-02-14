package package_Cookie;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class CookieService implements Serializable {
    @Inject
    private CookiePersistence db;
    
    public List<Cookie> cookies() {
        List <Cookie> result = new ArrayList<Cookie>();
        for(Cookie c : this.db.findAllCookies()) {
            result.add(c);
        }
        return result;
    }
    
    public List<Cookie> orderedCookies(int order) {
        List <Cookie> result = new ArrayList<Cookie>();
        MyOrder o = db.findOrder(order);
        for(OrderItem oi : o.getOrdered()) {
            result.add(db.findCookie(oi.getCookieId()));
        }
        return result;
    }
    
    public void addCookie(String name, double price, int count) {
        Cookie c = new Cookie();
        c.setName(name);
        c.setCount(count);
        c.setPrice(price);
        this.db.addCookie(c);
    }
    
    public void deleteCookie(int id) {
        this.db.removeCookie(id);
    } 
    
    public boolean isThereCookie(int id){ //TODO: test if findCookie can return null
        if(this.db.findCookie(id) != null){
            return true;
        }else{
            return false;
        }
    }
    //Wird nicht genutzt!
    public boolean isThereName(String name) { //TODO: is there an unique anntoation
        for(Cookie c : this.db.findAllCookies()) {
            if(c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public Cookie findCookie(int id){
        return this.db.findCookie(id);
    }
    
    public void deleteAllCookies() {
        for(Cookie c : this.db.findAllCookies()) {
            this.db.removeCookie(c.getId());
        }
    }
    
    public void updateCookie(Cookie c) {
        this.db.merge(c);
    }
}