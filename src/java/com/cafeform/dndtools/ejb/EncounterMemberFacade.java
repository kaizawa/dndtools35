/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.ejb;

import com.cafeform.dndtools.entity.EncounterMember;
import com.cafeform.dndtools.entity.EncounterRecord;
import com.cafeform.dndtools.entity.ScenarioCharacterRecord;
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
public class EncounterMemberFacade extends AbstractFacade<EncounterMember> {

    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncounterMemberFacade() {
        super(EncounterMember.class);
    }

    public List<EncounterMember> findByScenarioCharacterRecord(ScenarioCharacterRecord chara) {
        Query query = em.createNamedQuery("EncounterMember.findByScenarioCharacterRecord");
        query.setParameter("scenarioCharacterRecord", chara);
        return query.getResultList();
    }
    
    public List<EncounterMember> findByEncounterRecord(EncounterRecord record) {
        Query query = em.createNamedQuery("EncounterMember.findByEncounterRecord");
        query.setParameter("encounterRecord", record);
        return query.getResultList();
    }    

    public EncounterMember findByScenarioCharacterRecordAndEncounterRecord(ScenarioCharacterRecord chara, EncounterRecord record) {
        Query query = em.createNamedQuery("EncounterMember.findBySchenarioCharacterRecordAndEncounterRecord");
        query.setParameter("encounterRecord", record);        
        query.setParameter("scenarioCharacterRecord", chara);
        return (EncounterMember) query.getSingleResult();
    }
}
