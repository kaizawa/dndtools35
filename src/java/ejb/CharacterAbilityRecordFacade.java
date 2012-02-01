/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.CharacterAbilityRecord;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class CharacterAbilityRecordFacade extends AbstractFacade<CharacterAbilityRecord> {
    @PersistenceContext(unitName = "dndPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CharacterAbilityRecordFacade() {
        super(CharacterAbilityRecord.class);
    }
    
}
