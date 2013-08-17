package com.cafeform.dndtools.ejb;

import com.cafeform.dndtools.entity.ArmType3Master;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class ArmType3MasterFacade extends AbstractFacade<ArmType3Master> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArmType3MasterFacade() {
        super(ArmType3Master.class);
    }

}
