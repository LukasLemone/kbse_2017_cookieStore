/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_Cookie;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author tobias
 */
@Stateless
public class CookiePersistence {
    
    @Inject
    private EntityManager em;
    
    
    //basic persistence-stuff
    public void persist(Object object) {
        em.persist(object);
    }
    
    public Object merge(Object object) {
        return em.merge(object);
    }
    
    public void remove(Object object) {
        em.remove(object);
    }
    
    public void flush() {
        em.flush();
    }
    
    public void refresh(Object object) {
        em.refresh(object);
    }
    
    //--------------cookie-persistence-stuff-------------------------------------
    
    public Cookie findCookie(int id) {
        return this.em.find(Cookie.class, id);
    }
    
    public List<Cookie> findAllCookies() {
        System.out.println("-findAllCookies");
        return em.createNamedQuery("Cookie.findAll", Cookie.class).getResultList();
    }
    
    public void removeCookie(int id) {
        Cookie c = findCookie(id);
        remove(c);
    }
    
    public void addCookie(Cookie c) {
        try {
            this.persist(c);
        } catch(Exception e) {
            System.out.println("Persisistierfehler");
        }
    }
    
    
}
