/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.CampaignMaster;
import entity.CharacterRecord;
import entity.ScenarioCharacterRecord;
import entity.ScenarioRecord;
import java.util.ArrayList;
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
public class ScenarioCharacterRecordFacade extends AbstractFacade<ScenarioCharacterRecord> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ScenarioCharacterRecordFacade() {
        super(ScenarioCharacterRecord.class);
    }
    
   public List<ScenarioCharacterRecord> findByScenarioRecord(ScenarioRecord scenario) {

        // シナリオが指定されていなかったら
        if (scenario == null) {
            return new ArrayList<ScenarioCharacterRecord>();
        }
        
        Query query = em.createNamedQuery("ScenarioCharacterRecord.findByScenario");
        query.setParameter("scenario", scenario);
        return query.getResultList();        
    } 
    
}
