/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.ejb;

import com.cafeform.dndtools.entity.CampaignMaster;
import com.cafeform.dndtools.entity.CharacterRecord;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class CharacterRecordFacade extends AbstractFacade<CharacterRecord> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CharacterRecordFacade() {
        super(CharacterRecord.class);
    }

    public List<CharacterRecord> findByCampaignId(Integer id) {

        // キャンペーンが指定されていなかったら
        if (id == null) {
            // 空を返す場合
            //return new ArrayList<CharacterRecord>();
            // キャンペーン設定が無いキャラクターをリストする場合
            String jpqr = "select b from CharacterRecord b "
                    + "where b.campaignId is null "
                    + "order by b.id";
            @SuppressWarnings("unchecked")
            List<CharacterRecord> result = em.createQuery(jpqr).getResultList();
            return result;
        }

        //JPQL では Object として受け取る。
        //下のselect b の b は Bookmark のオブジェクトで受け取ることを意味する。
        //また where 句で使われている列名はデータベースの列名でなく、エンティティ
        //のフィールド名なので注意。
        CampaignMaster key = em.find(CampaignMaster.class, id);
        String jpqr = "select b from CharacterRecord b "
                + "where b.campaignId = :id "
                + "order by b.saveTime DESC";
        @SuppressWarnings("unchecked")
        List<CharacterRecord> result = em.createQuery(jpqr).setParameter("id", key).getResultList();
        return result;
    }
    
    public int countByCampaignId(Integer id){
        return findByCampaignId(id).size();
    }
    
    @Override
    public List<CharacterRecord> findAll() {
        String jpqr = "select b from CharacterRecord b "
                + "order by b.saveTime DESC";
        @SuppressWarnings("unchecked")
        List<CharacterRecord> result = em.createQuery(jpqr).getResultList();
        return result;
    }    
    
    @Override
    public List<CharacterRecord> findRange(int[] range) {
        javax.persistence.Query q = getEntityManager().createQuery("select b from CharacterRecord b " 
                + "order by b.saveTime DESC");
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    
    public List<CharacterRecord> findRangeByCampaignId(int[] range, Integer id) {
        CampaignMaster key = em.find(CampaignMaster.class, id);
        javax.persistence.Query q = getEntityManager().createQuery("select b from CharacterRecord b " 
                + "where b.campaignId = :id "
                + "order by b.saveTime DESC");
        q.setParameter("id", key);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }  
    
    public CharacterRecord findById(Integer id) {
        return em.find(CharacterRecord.class, id);
    } 
    
    public List<CharacterRecord> findByCampaign(CampaignMaster campaign) {
        String jpqr = "select b from CharacterRecord b "
                + "where b.campaignId = :id "
                + "order by b.saveTime DESC";
        @SuppressWarnings("unchecked")
        List<CharacterRecord> result = em.createQuery(jpqr).setParameter("id", campaign).getResultList();
        return result;
    }
    public List<CharacterRecord> findRangeByCampaign(int[] range, CampaignMaster campaign) {
        javax.persistence.Query q = getEntityManager().createQuery("select b from CharacterRecord b " 
                + "where b.campaignId = :id "
                + "order by b.saveTime DESC");
        q.setParameter("id", campaign);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    } 
    
    public int countByCampaign(CampaignMaster campaign){
        return findByCampaign(campaign).size();
    }
}
