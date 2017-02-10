package package_Cookie;
 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    private List<Cookie> order;
    
    @Inject
    private CookieService cs;

    @PostConstruct
    public void init() {
        this.order = new ArrayList<>();
        cs.deleteAllCookies();
        
        cs.addCookie("Schoko", 1.99, 64);
        cs.addCookie("Halbkorn", 2.49, 64);
        cs.addCookie("Osmania", 2.99, 64);
        cs.addCookie("Schokomilch", 1.49, 64);
        cs.addCookie("Vollkorn", 0.99, 64);
    }

    //Buttons in main.xhtml
    public void orderCookieButton(int toOrderId) {
        if(cs.findCookie(toOrderId).getCount() < orderCount){
            addMessage("Vorrat reicht nicht aus");
        }else{
            String nName = cs.findCookie(toOrderId).getName();
            double nPrice = cs.findCookie(toOrderId).getPrice();
            int nCount = orderCount;
            
            Cookie toOrder = new Cookie(nName, nPrice, nCount);
            this.order.add(toOrder);
            
            cs.findCookie(toOrderId).setCount(cs.findCookie(toOrderId).getCount() - orderCount);
            addMessage("Zur Bestellung hinzugefügt");
        }  
    }
    
    public void addCookieButton() {
        if(!toAddName.equals(null) && toAddPrice != 0 && toAddCount != 0){
            cs.addCookie(toAddName, toAddPrice, toAddCount);
            addMessage("Cookie hinzugefügt");
        }else{
            addMessage("Angaben unvollständig");
        }
    }
    
    public void deleteCookieButton() {
        if(cs.isThereCookie(idToDelete)){
            cs.deleteCookie(idToDelete);            
            addMessage("Cookie gelöscht");
        }else{
            addMessage("Cookie nicht gefunden");
        }
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
        return this.order;
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //Getter and Setter
    public List<Cookie> getOrder() {
        return order;
    } 

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

    public void setOrder(List<Cookie> order) {
        this.order = order;
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