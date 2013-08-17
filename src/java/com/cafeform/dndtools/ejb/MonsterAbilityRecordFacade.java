/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.ejb;

import com.cafeform.dndtools.entity.MonsterAbilityRecord;
import com.cafeform.dndtools.entity.MonsterMaster;
import com.cafeform.dndtools.entity.MonsterSaveRecord;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kaizawa
 */
@Stateless
public class MonsterAbilityRecordFacade extends AbstractFacade<MonsterAbilityRecord> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MonsterAbilityRecordFacade() {
        super(MonsterAbilityRecord.class);
    }
    
    public List<MonsterAbilityRecord> findByMonsterMaster(MonsterMaster monster){
        Query query = em.createNamedQuery("MonsterAbilityRecord.findByMonsterMaster");
        query.setParameter("monster", monster);
        return query.getResultList();
    }
    
}
