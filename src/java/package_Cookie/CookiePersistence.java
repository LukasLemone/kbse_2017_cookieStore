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
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    public Object merge(Object object) {
        return em.merge(object);
    }
    
    public List<Cookie> findAllCookies() {
        List<Cookie> erg = new ArrayList<>();
        return erg;
    }
}
