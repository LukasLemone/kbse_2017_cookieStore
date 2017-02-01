/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageForCookieStuff;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tobias
 */
public class PersistenceProducer {
    @Produces
    @PersistenceContext(unitName = "Kbse_2017_Luto_PU")
    private EntityManager em;
}
