/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.ScenarioCharacterRecord;
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
    
}
