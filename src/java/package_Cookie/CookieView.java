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
    
    private MyOrder myOrder;
    
    @Inject
    private CookieService cs;
    
    @Inject
    private OrderService os;

    @PostConstruct
    public void init() {
        cs.deleteAllCookies();
        os.deleteAllOrders();
        
        cs.addCookie("Schoko", 1.99, 64);
        cs.addCookie("Halbkorn", 2.49, 64);
        cs.addCookie("Osmania", 2.99, 64);
        cs.addCookie("Schokomilch", 1.49, 64);
        cs.addCookie("Vollkorn", 0.99, 64);
        
        myOrder = new MyOrder();
        os.addOrder(myOrder);
        orderCount = 0;
    }

    //Buttons in main.xhtml ----------------------------------------------------
    public void orderCookieButton(int toOrderId) {
        if(cs.findCookie(toOrderId).getCount() < orderCount){
            addMessage("Vorrat reicht nicht aus");
        }else if(orderCount <= 0){
            addMessage("Bitte Menge angeben");
        }else{
            os.addOrderItem(myOrder.getId(), toOrderId, orderCount);
            addMessage("Zur Bestellung hinzugefügt");
        }
        orderCount = 0;
    }
    
    public void addCookieButton() {
        if(!toAddName.equals(null) && toAddPrice != 0 && toAddCount != 0){
            cs.addCookie(toAddName, toAddPrice, toAddCount);
            toAddName= "";
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
    
    //Buttons in order.xhtml ---------------------------------------------------
    public void orderDeleteCookieButton(int toDeleteId) {
        //os.deleteOrderItem(toDeleteId, oi);
        //addMessage("Von Bestellung gelöscht");
    }
    public String confirmOrderButton() {
        if(getOrderPrice() == 0){
            addMessage("Bestellung ist leer");
            return null;
        }else{
            for(OrderItem oi : os.allOrderItems(myOrder.getId())) {
                if(!cs.isThereCookie(oi.getCookieId())) {
                    addMessage("Cookie "+oi.getCookieId()+" existiert nicht mehr");
                    rewind();
                    break;
                } else {
                    if(!(oi.getCount() <= cs.findCookie(oi.getCookieId()).getCount())) {
                        addMessage("Cookie "+oi.getCookieId()+" existiert nicht mehr in der Stückzahl");
                        rewind();
                        break;
                    } else {
                        //Bestellposten ausführen
                        addMessage("DEBUG: "+oi.getCount()+"|"+oi.getCookieId());
                        Cookie c = cs.findCookie(oi.getCookieId());
                        c.setCount(c.getCount() - oi.getCount());
                        cs.updateCookie(c);

                        //Bestellstatus auf positiv
                        oi.setStatus(true);
                        os.updateOrderItem(oi);
                    }
                }
            }
            
            myOrder.setCustomer(toAddName);
            os.updateOrder(myOrder);
            toAddName = "";
            
            //Aufräumen
            addMessage("Bestellung erfolgreich");
            orderCount = 0;
            myOrder = new MyOrder();
            os.addOrder(myOrder);
            os.addOrder(myOrder);
                
            return "final?faces-redirect=true";
        }
    }
    
    //Funktionalität -----------------------------------------------------------
    //Wird nicht genutzt!
    public int getOrderCount(int id) {        
        OrderItem oi = os.findOrderItemByCookie(id, this.myOrder.getId());
        return oi.getCount();
    }
    
    //Wird nicht genutzt!
    public double getOrderPrice() {
        double temp = os.getOrderPrice(myOrder.getId());
        return Math.round(temp*100.0)/100.0;
    }
    
    //Wird nicht genutzt!
    public double getOrderItemPrice(int id) {
        OrderItem oi = os.findOrderItemByCookie(id, this.myOrder.getId());
        return  oi.getCount() * cs.findCookie(id).getPrice();
    }
    
    //Bestellung bei Fehler wieder rückgängig machen
    public void rewind() {
        for(OrderItem oi : os.allOrderItems(myOrder.getId())) {
            if(oi.isStatus() == true) {
                //Bearbeitete Posten wieder hochzählen
                Cookie c = cs.findCookie(oi.getCookieId());
                c.setCount(c.getCount() + oi.getCount());
                cs.updateCookie(c);
                
                //Status des Bestellpostens zurücksetzen
                oi.setStatus(false);
                os.updateOrderItem(oi);
            }
        }
    }
    
    public List<Cookie> cookies() {
        return cs.cookies();
    }
    
    public List<Cookie> orderedCookies() {
        return cs.orderedCookies(myOrder.getId());
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //Getter & Setter ----------------------------------------------------------
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