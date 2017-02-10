package package_Cookie;
 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named(value = "CookieView")
public class CookieView implements Serializable {
    
    @Inject
    private CookieService cs;
    
    @PostConstruct
    public void init() {

    }
        
    private String toAddName;
    private double toAddPrice;
    private int toAddCount;
    private Long idToDelete;

    //Buttons in main.xhtml, growl message, get cookie list
    public void orderCookieButton() {
        
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

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public List<Cookie> cookies() {
        return cs.cookies();
    }
    
    //Getter and Setter (required for JSF to resolve class variables)
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

    public Long getIdToDelete() {
        return idToDelete;
    }

    public void setCs(CookieService cs) {
        this.cs = cs;
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