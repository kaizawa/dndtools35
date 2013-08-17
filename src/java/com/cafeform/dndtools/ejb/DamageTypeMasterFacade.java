package com.cafeform.dndtools.ejb;

import com.cafeform.dndtools.entity.DamageTypeMaster;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class DamageTypeMasterFacade extends AbstractFacade<DamageTypeMaster> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DamageTypeMasterFacade() {
        super(DamageTypeMaster.class);
    }

}
