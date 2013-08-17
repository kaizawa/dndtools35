package com.cafeform.dndtools.ejb;

import com.cafeform.dndtools.entity.DiceMaster;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class DiceMasterFacade extends AbstractFacade<DiceMaster> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiceMasterFacade() {
        super(DiceMaster.class);
    }

}
