/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.ClassMaster;
import entity.ClassSkillMaster;
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
public class ClassSkillMasterFacade extends AbstractFacade<ClassSkillMaster> {
    @PersistenceContext(unitName = "dndPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassSkillMasterFacade() {
        super(ClassSkillMaster.class);
    }
        public List<ClassSkillMaster> findByClass(ClassMaster classMaster) {


        // キャラクターが渡されていた無かったら空のリストを渡す
        if(classMaster == null ){
            return new ArrayList<ClassSkillMaster>();
        }

        String jpqr = "select g from ClassSkillMaster g " +
                "where g.classMaster = :cls " +
                "order by g.classSkillMasterPK.skillId";
        @SuppressWarnings("unchecked")
        List<ClassSkillMaster> result = em.createQuery(jpqr).setParameter("cls", classMaster).getResultList();
        return result;
    }
}
