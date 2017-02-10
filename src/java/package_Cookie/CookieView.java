package package_Cookie;
 
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
<<<<<<< HEAD
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named(value = "CookieView")
=======
import javax.inject.Named;

@Named("CookieView")
@SessionScoped
>>>>>>> 8b97b48a8097ef46b24d48e0c2531962978b202e
public class CookieView implements Serializable {
    
    @Inject
    private CookieService cs;
    
    private String toAddName;
    private double toAddPrice;
    private int toAddCount;
    private int idToDelete;
<<<<<<< HEAD

    //Buttons in main.xhtml, growl message, get cookie list
    public void orderCookieButton() {
=======
    
    @PostConstruct
    public void init() {
>>>>>>> 8b97b48a8097ef46b24d48e0c2531962978b202e
        
        cs.addCookie("Zimtstern", 10.8, 30);
        cs.addCookie("Brownies", 21.3, 3);
        cs.addCookie("Makronen", 2.3, 22);
    }

    //Buttons in main.xhtml, growl message, get cookie list
    public void addCookieButton() {
        System.out.println("addCookieButton");
        addMessage("Cookie hinzugefügt");
        cs.addCookie(toAddName, toAddPrice, toAddCount);
    }
    
    public void deleteCookieButton() {
        System.out.println("deleteCookieButton");
        addMessage("Cookie gelöscht");
        cs.deleteCookie(idToDelete);
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public List<Cookie> cookies() {
        return cs.cookies();
    }
    
<<<<<<< HEAD
    public List<Cookie> orderedCookies() {
        //TODO get ordered Cookies
        return null;
    }
    
    //Getter and Setter (required for JSF to resolve class variables)
    public CookieService getCs() {
        return cs;
    } 
=======
    //Getter and Setter (required for JSF to resolve class variables)    
>>>>>>> 8b97b48a8097ef46b24d48e0c2531962978b202e
    
     public String getToAddName() {
        return toAddName;
    }

    public double getToAddPrice() {
        return toAddPrice;
    }

    public int getToAddCount() {
        return toAddCount;
    }

    public int getIdToDelete() {
        return idToDelete;
    }
    
    public void setToAddName(String toAddName) {
        this.toAddName = toAddName;
    }

    public void setToAddPrice(double toAddPrice) {
        this.toAddPrice = toAddPrice;
    }

    public void setToAddCount(int toAddCount) {
        this.toAddCount = toAddCount;
    }

    public void setIdToDelete(int idToDelete) {
        this.idToDelete = idToDelete;
    }
}