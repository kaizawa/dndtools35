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

        //JPQL では Object として受け取る。
        //下のselect c の c は ScenarioCharacterRecord のオブジェクトで受け取ることを意味する。
        //また where 句で使われている列名はデータベースの列名でなく、エンティティ
        //のフィールド名なので注意。
        String jpqr = "select c from ScenarioCharacterRecord c " +
                "where c.scenario = :scenario ";
        @SuppressWarnings("unchecked")
        List<ScenarioCharacterRecord> result = em.createQuery(jpqr).setParameter("scenario", scenario).getResultList();
        return result;
    } 
    
}
