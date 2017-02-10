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
    
    //return all cookies as list
    public List<Cookie> cookies() {
        List <Cookie> erg = new ArrayList<Cookie>();
        for(Cookie c : this.db.findAllCookies()) {
            erg.add(c);
        }
        return erg;
    }
    
    //add cookie with parameters
    public void addCookie(String name, double price, int count) {
        Cookie c = new Cookie();
        c.setName(name);
        c.setCount(count);
        c.setPrice(price);
        this.db.addCookie(c);
    }
    
    //delete cookie with id
    public void deleteCookie(int id) {
        this.db.removeCookie(id);
    } 
    
    //
    public void deleteAllCookies() {
        for(Cookie c : this.db.findAllCookies()) {
            this.db.removeCookie(c.getId());
        }
    }
}