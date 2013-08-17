package com.cafeform.dndtools.ejb;

import com.cafeform.dndtools.entity.ArmMaster;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class ArmMasterFacade extends AbstractFacade<ArmMaster> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArmMasterFacade() {
        super(ArmMaster.class);
    }

}
