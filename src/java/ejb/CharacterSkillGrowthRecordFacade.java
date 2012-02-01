/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.CharacterGrowthRecord;
import entity.CharacterRecord;
import entity.CharacterSkillGrowthRecord;
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
public class CharacterSkillGrowthRecordFacade extends AbstractFacade<CharacterSkillGrowthRecord> {
    @PersistenceContext(unitName = "dndPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CharacterSkillGrowthRecordFacade() {
        super(CharacterSkillGrowthRecord.class);
    }
    public List<CharacterSkillGrowthRecord> findByCharacterAndLevel(CharacterRecord characterRecord, Integer lv) {


        //キャラクターが渡されていた無かったら null を返す        
        if (lv == null || characterRecord == null) {
            return null;
        }
        
        String jpqr = "select g from CharacterSkillGrowthRecord g " +
                "where g.characterRecord = :chara " +
                "and g.characterSkillGrowthRecordPK.characterLevel = :level " +
                "order by g.characterLevel, g.skillId";
        @SuppressWarnings("unchecked")        
        List<CharacterSkillGrowthRecord>  result = (List<CharacterSkillGrowthRecord>) em.createQuery(jpqr).setParameter("chara", characterRecord).setParameter("level", lv).getResultList();
        return result;
    }

    public List<CharacterSkillGrowthRecord> findByCharacter(CharacterRecord characterRecord) {


        // Lv や キャラクターが渡されていた無かったら空のリストを渡す
        if(characterRecord == null ){
            return new ArrayList<CharacterSkillGrowthRecord>();
        }

        String jpqr = "select g from CharacterSkillGrowthRecord g " +
                "where g.characterRecord = :chara " +
                "order by g.characterSkillGrowthRecordPK.characterLevel, g.characterSkillGrowthRecordPK.skillId";
        @SuppressWarnings("unchecked")                
        List<CharacterSkillGrowthRecord>  result = em.createQuery(jpqr).setParameter("chara", characterRecord).getResultList();
        return result;
    }    
}
