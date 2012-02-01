/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.SizeMaster;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class SizeMasterFacade extends AbstractFacade<SizeMaster> {
    @PersistenceContext(unitName = "dndPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SizeMasterFacade() {
        super(SizeMaster.class);
    }
    
}
