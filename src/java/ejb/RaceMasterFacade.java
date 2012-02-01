/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.RaceMaster;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class RaceMasterFacade extends AbstractFacade<RaceMaster> {
    @PersistenceContext(unitName = "dndPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RaceMasterFacade() {
        super(RaceMaster.class);
    }
    
}
