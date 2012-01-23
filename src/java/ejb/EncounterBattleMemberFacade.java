/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.EncounterBattleMember;
import entity.EncounterCharacter;
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
public class EncounterBattleMemberFacade extends AbstractFacade<EncounterBattleMember> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncounterBattleMemberFacade() {
        super(EncounterBattleMember.class);
    }
    
    public List<EncounterBattleMember> findByEncounterCharacter(EncounterCharacter encounterCharacter){
        Query query = em.createNamedQuery("EncounterBattleMember.findByEncounterCharacter");
        query.setParameter("encounterCharacter", encounterCharacter);
        return query.getResultList();
    }
    
}
