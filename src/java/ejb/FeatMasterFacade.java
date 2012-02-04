/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.FeatMaster;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class FeatMasterFacade extends AbstractFacade<FeatMaster> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeatMasterFacade() {
        super(FeatMaster.class);
    }
    
}
