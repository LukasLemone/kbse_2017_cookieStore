package package_Cookie;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
 
@ApplicationScoped
@Named("cookieService")
public class CookieService implements Serializable {
    /*TODO: Database Connection*/
    
    private CookiePersistence db;
 
    public List<Cookie> createCookies_Debug() {
        List<Cookie> list = new ArrayList<>();
        
        Cookie newCookieBlue = new Cookie(1, "Blue", 2.49, 50);
        Cookie newCookieGreen = new Cookie(2, "Green", 2.49, 50);
        Cookie newCookieMM = new Cookie(3, "MM", 3.49, 50);
        Cookie newCookieNormal = new Cookie(4, "Normal", 1.99, 50);
        
        list.add(newCookieBlue);
        list.add(newCookieGreen);
        list.add(newCookieMM);
        list.add(newCookieNormal);
         
        return list;
    }
    
    public List<Cookie> cookies() {
        List <Cookie> erg = new ArrayList<>();
        return erg;
    }
    
    public void addCookie(String name, double prize, int count) {
        
    }
    
    public void deleteCookie(int id) {
        
    }
     
}