/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.MonsterMaster;
import entity.MonsterSaveRecord;
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
public class MonsterSaveRecordFacade extends AbstractFacade<MonsterSaveRecord> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MonsterSaveRecordFacade() {
        super(MonsterSaveRecord.class);
    }
    
    public List<MonsterSaveRecord> findByMonsterMaster(MonsterMaster monster){
        Query query = em.createNamedQuery("MonsterSaveRecord.findByMonsterMaster");
        query.setParameter("monster", monster);
        return query.getResultList();
    }
}
