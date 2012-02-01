/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.ClassMaster;
import entity.ClassSaveMaster;
import entity.SaveMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class ClassSaveMasterFacade extends AbstractFacade<ClassSaveMaster> {
    @PersistenceContext(unitName = "dndPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassSaveMasterFacade() {
        super(ClassSaveMaster.class);
    }
 
    public ClassSaveMaster findByClassAndSave(ClassMaster classMaster, SaveMaster save) {
        ClassSaveMaster result;

        // class や save が渡されていた無かったらnullを渡す
        if(classMaster == null || save == null){
            return null;
        }

        String jpqr = "select g from ClassSaveMaster g " +
                "where g.classMaster = :klass " +
                "and g.saveMaster = :save";
        result = (ClassSaveMaster) em.createQuery(jpqr).setParameter("klass", classMaster).setParameter("save", save).getSingleResult();
        return result;
    }

    public List<ClassSaveMaster> findByClass(ClassMaster classMaster) {


        // class が渡されていた無かったらnullを渡す
        if(classMaster == null ){
            return null;
        }

        String jpqr = "select g from ClassSaveMaster g " +
                "where g.classMaster = :klass " +
                " order by g.classSaveMasterPK.saveId";
        @SuppressWarnings("unchecked")                                                
        List<ClassSaveMaster> result = em.createQuery(jpqr).setParameter("klass", classMaster).getResultList();
        return result;
    }   
}
