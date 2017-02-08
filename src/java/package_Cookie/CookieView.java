package package_Cookie;
 
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//@Named
//@ViewScoped
@ManagedBean
//@ViewScoped
public class CookieView /*implements Serializable*/ {
    
    @ManagedProperty("#{cookieService}")
    private CookieService service;
    
    private List<Cookie> cookies;
    private String toAddName;
    private double toAddPrice;
    private int toAddCount;
    
    public void addCookieButton(ActionEvent actionEvent) {
        addMessage("Cookie hinzugefügt");
    }
    
    public void deleteCookieButton(ActionEvent actionEvent) {
        addMessage("Cookie gelöscht");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
 /*         
    public CookieView() {
        this.cookies = new ArrayList<>();
        
        Cookie newCookieBlue = new Cookie(1, "blue", 2.49, 50);
        Cookie newCookieGreen = new Cookie(2, "green", 2.49, 50);
        Cookie newCookieMM = new Cookie(3, "mm", 3.49, 50);
        Cookie newCookieNormal = new Cookie(4, "normal", 1.99, 50);
        
        this.cookies.add(newCookieBlue);
        this.cookies.add(newCookieGreen);
        this.cookies.add(newCookieMM);
        this.cookies.add(newCookieNormal);
    }
  */  
    //public List<Cookie> getCookies() {
    //    return cookies;
    //}
    
    //public void setService(CookieService service) {
    //    this.service = service;
    //}
}