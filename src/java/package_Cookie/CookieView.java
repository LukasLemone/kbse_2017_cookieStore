package package_Cookie;
 
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;



@Named("CookieView")
@SessionScoped
public class CookieView implements Serializable {
    
    @Inject
    private CookieService cs;
    
    
    private String toAddName;
    private double toAddPrice;
    private int toAddCount;
    private Long idToDelete;
    
    public void addCookieButton() {
        System.out.println("addCookieButton");
        addMessage("Cookie hinzugefügt");
        cs.addCookie(toAddName, toAddPrice, toAddCount);
    }
    
    public void deleteCookieButton() {
        System.out.println("deleteCookieButton");
        addMessage("Cookie gelöscht");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public List<Cookie> cookies() {
        
        return cs.cookies();
    }
    
    @PostConstruct
    public void init() {
        cs.addCookie("Zimtstern", 10.8, 30);
        cs.addCookie("Brownies", 21.3, 3);
        cs.addCookie("Makronen", 2.3, 22);
        
    }
    
    
    //Getter and Setter (required for JSF to resolve class variables)
     public String getToAddName() {
        return toAddName;
    }

    public double getToAddPrice() {
        return toAddPrice;
    }

    public int getToAddCount() {
        return toAddCount;
    }

    public Long getIdToDelete() {
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

    public void setIdToDelete(Long idToDelete) {
        this.idToDelete = idToDelete;
    }

}