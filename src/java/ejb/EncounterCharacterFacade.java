/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.EncounterCharacter;
import entity.Playercharacter;
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
public class EncounterCharacterFacade extends AbstractFacade<EncounterCharacter> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncounterCharacterFacade() {
        super(EncounterCharacter.class);
    }
    
    public List<EncounterCharacter> findByPlayerCharacter(Playercharacter playerCharacter){
        Query query = em.createNamedQuery("EncounterCharacter.findByplayerCharacter");
        query.setParameter("playerCharacter", playerCharacter);
        return query.getResultList();
    }
    
}
