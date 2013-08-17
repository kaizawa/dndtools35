/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.ejb;

import com.cafeform.dndtools.entity.EncounterMember;
import com.cafeform.dndtools.entity.EncounterRecord;
import com.cafeform.dndtools.entity.ScenarioRecord;
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
public class EncounterRecordFacade extends AbstractFacade<EncounterRecord> {

    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncounterRecordFacade() {
        super(EncounterRecord.class);
    }
    
    public List<EncounterRecord> findByScenarioRecord(ScenarioRecord scenario) {
        Query query = em.createNamedQuery("EncounterRecord.findByScenarioRecord");
        query.setParameter("scenarioRecord", scenario);
        return query.getResultList();
    }
    
    public List<EncounterRecord> findByScenarioRecordRange(ScenarioRecord scenario, int[] range) {
        Query query = em.createNamedQuery("EncounterRecord.findByScenarioRecord");
        query.setParameter("scenarioRecord", scenario);
        query.setMaxResults(range[1] - range[0]);
        query.setFirstResult(range[0]);
        return query.getResultList();
    }
    
    public int countByScenarioRecord(ScenarioRecord scenario) {
        return findByScenarioRecord(scenario).size();
    }
}
