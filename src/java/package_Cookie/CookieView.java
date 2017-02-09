package package_Cookie;
 
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

//@Named("CookieView")
//@SessionScoped
//@ViewScoped
//@ManagedBean
@ManagedBean
@SessionScoped
public class CookieView implements Serializable {
    
    @Inject
    private CookieService cs;
    
    @PostConstruct
    public void init() {
        this.cookies = new ArrayList<>();
        Cookie c = new Cookie("Test", 1.5, 64);
        this.cookies.add(c);
        
        //cs.addCookie("Zimtstern", 10.8, 30);
        //cs.addCookie("Brownies", 21.3, 3);
        //cs.addCookie("Makronen", 2.3, 22);
    }
    
    private List<Cookie> cookies;
    
    private String toAddName;
    private double toAddPrice;
    private int toAddCount;
    private Long idToDelete;

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
    
    //Getter and Setter (required for JSF to resolve class variables)
    public CookieService getCs() {
        return cs;
    }

    public List<Cookie> getCookies() {
        return cookies;
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

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
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