/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.SkillMaster;
import entity.SkillSynergyMaster;
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
public class SkillSynergyMasterFacade extends AbstractFacade<SkillSynergyMaster> {
    @PersistenceContext(unitName = "dndPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SkillSynergyMasterFacade() {
        super(SkillSynergyMaster.class);
    }
  
    public List<SkillSynergyMaster> findBySkill(SkillMaster skill) {


        // skill が渡されていた無かったらnullを渡す
        if( skill == null){
            return new ArrayList<SkillSynergyMaster>();
        }

        String jpqr = "select g from SkillSynergyMaster g " +
                "where g.skillMaster = :skill";
        @SuppressWarnings("unchecked")
        List<SkillSynergyMaster> result = em.createQuery(jpqr).setParameter("skill", skill).getResultList();
        return result;
    }
}
