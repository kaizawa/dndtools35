/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.EncounterMember;
import entity.EncounterCharacter;
import entity.EncounterRecord;
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

    public List<EncounterMember> findByEncounterCharacter(EncounterCharacter chara) {
        Query query = em.createNamedQuery("EncounterMember.findByEncounterCharacter");
        query.setParameter("encounterCharacter", chara);
        return query.getResultList();
    }
    
    public List<EncounterMember> findByEncounterRecord(EncounterRecord record) {
        Query query = em.createNamedQuery("EncounterMember.findByEncounterRecord");
        query.setParameter("encounterRecord", record);
        return query.getResultList();
    }    
    
    public List<EncounterMember> findByEncounterCharacterAndEncounterRecord(EncounterCharacter chara, EncounterRecord record) {
        Query query = em.createNamedQuery("EncounterMember.findByEncounterCharacterAndEncounterRecord");
        query.setParameter("encounterRecord", record);
        query.setParameter("encounterCharacter", chara);
        return query.getResultList();
    }      
}
