/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.CampaignMaster;
import entity.ScenarioRecord;
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
public class ScenarioRecordFacade extends AbstractFacade<ScenarioRecord> {

    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ScenarioRecordFacade() {
        super(ScenarioRecord.class);
    }

    public List<ScenarioRecord> findByCampaignId(Integer campaignId) {
        CampaignMaster campaign = em.find(CampaignMaster.class, campaignId);
        Query q = getEntityManager().createQuery("select s from ScenarioRecord s "
                + "where s.campaign = :campaign");
        q.setParameter("campaign", campaign);
        return q.getResultList();
    }

    public List<ScenarioRecord> findRangeByCampaignId(int[] range, Integer campaignId) {
        CampaignMaster campaign = em.find(CampaignMaster.class, campaignId);
        Query q = getEntityManager().createQuery("select s from ScenarioRecord s "
                + "where s.campaign = :campaign");
        q.setParameter("campaign", campaign);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int countByCampaignId(Integer id) {
        return findByCampaignId(id).size();
    }

    public List<ScenarioRecord> findRangeByCampaign(int[] range, CampaignMaster campaign) {
        Query q = getEntityManager().createQuery("select s from ScenarioRecord s "
                + "where s.campaign = :campaign");
        q.setParameter("campaign", campaign);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    public int countByCampaign(CampaignMaster campaign) {
        return findByCampaign(campaign).size();
    }
    
    public List<ScenarioRecord> findByCampaign(CampaignMaster campaign) {
        Query q = getEntityManager().createQuery("select s from ScenarioRecord s "
                + "where s.campaign = :campaign");
        q.setParameter("campaign", campaign);
        return q.getResultList();
    }
}
