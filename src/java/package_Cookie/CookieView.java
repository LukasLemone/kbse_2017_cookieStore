package packageForCookieStuff;
 
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
 
@ViewScoped
@Named
public class CookieView implements Serializable {
     
    private List<Cookie> cookies;
         
    @ManagedProperty("#{cookieService}")
    private CookieService service;
     
    @PostConstruct
    public void init() {
        cookies = service.createCookies();
    }
 
    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setService(CookieService service) {
        this.service = service;
    }
}