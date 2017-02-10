package package_Cookie;
 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("CookieView")
@SessionScoped
public class CookieView implements Serializable {
    
    private String toAddName;
    private double toAddPrice;
    private int toAddCount;
    private int idToDelete;
    private int orderCount;
    
    @Inject
    private CookieService cs;

    @PostConstruct
    public void init() {        
        cs.deleteAllCookies();
        
        cs.addCookie("Zimtstern", 1.99, 64);
        cs.addCookie("Brownie", 2.49, 64);
        cs.addCookie("Makrone", 2.99, 64);
        cs.addCookie("Butterkeks", 1.49, 64);
        cs.addCookie("Cookie", 0.99, 64);
    }

    //Buttons in main.xhtml
    public void orderCookieButton(int toOrderId) {
        //TODO add ordered List and add Cookie
    }
    
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
    
    //Buttons in final.xhtml
    public void confirmOrderButton() {
        //TODO confirm order
    }
    
    //Functionality
    public List<Cookie> cookies() {
        return cs.cookies();
    }
    
    public List<Cookie> orderedCookies() {
        //TODO return ordered Cookies
        return null;
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //Getter and Setter
    public int getOrderCount() {
        return orderCount;
    } 

    public CookieService getCs() {
        return cs;
    } 
     
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

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
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