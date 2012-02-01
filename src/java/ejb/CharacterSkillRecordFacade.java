/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.CharacterSkillRecord;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class CharacterSkillRecordFacade extends AbstractFacade<CharacterSkillRecord> {
    @PersistenceContext(unitName = "dndPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CharacterSkillRecordFacade() {
        super(CharacterSkillRecord.class);
    }
    
}
