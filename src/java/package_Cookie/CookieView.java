package package_Cookie;
 
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
 
@ViewScoped
@Named
public class CookieView implements Serializable {
     
    private List<Cookie> cookies;
         
    @ManagedProperty("#{cookieService}")
    private CookieService service;
     
    @PostConstruct
    public void init() {
        //cookies = service.createCookies_Debug();
    }
    
    public void buttonAction(ActionEvent actionEvent) {
        addMessage("Welcome to Primefaces!!");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    public List<Cookie> getCookies() {
        return cookies;
    }
    public void setService(CookieService service) {
        this.service = service;
    }
}