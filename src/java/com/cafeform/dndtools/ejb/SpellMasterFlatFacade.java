/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.ejb;

import com.cafeform.dndtools.entity.AbilityMaster;
import com.cafeform.dndtools.entity.SpellMasterFlat;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class SpellMasterFlatFacade extends AbstractFacade<SpellMasterFlat> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SpellMasterFlatFacade() {
        super(SpellMasterFlat.class);
    }
    
}
