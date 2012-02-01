/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.CharacterLevelMaster;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class CharacterLevelMasterFacade extends AbstractFacade<CharacterLevelMaster> {
    @PersistenceContext(unitName = "dndPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CharacterLevelMasterFacade() {
        super(CharacterLevelMaster.class);
    }
    
}
