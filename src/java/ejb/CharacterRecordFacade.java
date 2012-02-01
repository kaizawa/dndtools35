/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.CampaignMaster;
import entity.CharacterRecord;
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
    @PersistenceContext(unitName = "dndPU")
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
            String jpqr = "select b from CharacterRecord b " +
                    "where b.campaignId is null " +
                    "order by b.id";
            @SuppressWarnings("unchecked")
            List<CharacterRecord> result = em.createQuery(jpqr).getResultList();
            return result;
        }

        //JPQL では Object として受け取る。
        //下のselect b の b は Bookmark のオブジェクトで受け取ることを意味する。
        //また where 句で使われている列名はデータベースの列名でなく、エンティティ
        //のフィールド名なので注意。
        CampaignMaster key = em.find(CampaignMaster.class, id);        
        String jpqr = "select b from CharacterRecord b " +
                "where b.campaignId = :id " +
                "order by b.id";
        @SuppressWarnings("unchecked")
        List<CharacterRecord> result = em.createQuery(jpqr).setParameter("id", key).getResultList();
        return result;
    }   
}
