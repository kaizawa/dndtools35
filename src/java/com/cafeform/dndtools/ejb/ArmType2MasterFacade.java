package com.cafeform.dndtools.ejb;

import com.cafeform.dndtools.entity.ArmType2Master;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class ArmType2MasterFacade extends AbstractFacade<ArmType2Master> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArmType2MasterFacade() {
        super(ArmType2Master.class);
    }

}
