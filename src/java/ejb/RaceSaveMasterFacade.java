/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.RaceMaster;
import entity.RaceSaveMaster;
import entity.SaveMaster;
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
public class RaceSaveMasterFacade extends AbstractFacade<RaceSaveMaster> {
    @PersistenceContext(unitName = "dndPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RaceSaveMasterFacade() {
        super(RaceSaveMaster.class);
    }

    public List<RaceSaveMaster> findByRace(RaceMaster race) {

        // class が渡されていた無かったらnullを渡す
        if(race == null ){
            return new ArrayList<RaceSaveMaster>();
        }

        String jpqr = "select g from RaceSaveMaster g " +
                "where g.raceMaster = :race " +
                " order by g.raceSaveMasterPK.saveId";
        @SuppressWarnings("unchecked")
        List<RaceSaveMaster> result = em.createQuery(jpqr).setParameter("race", race).getResultList();
        return result;
    }

    public RaceSaveMaster findByRaceAndSave(RaceMaster race, SaveMaster save) {
       RaceSaveMaster result;

        // class が渡されていた無かったらnullを渡す
        if(race == null || save == null){
            return null;
        }

        String jpqr = "select g from RaceSaveMaster g " +
                "where g.raceMaster = :race " +
                "and g.saveMaster = :save " +
                " order by g.raceSaveMasterPK.saveId";
        result = (RaceSaveMaster)em.createQuery(jpqr).setParameter("race", race).setParameter("save", save).getSingleResult();
        return result;
    }    
}
