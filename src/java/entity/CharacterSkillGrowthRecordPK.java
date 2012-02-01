/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ka78231
 */
@Embeddable
public class CharacterSkillGrowthRecordPK implements Serializable {
    @Column(name = "CHARACTER_ID", nullable = false)
    private int characterId;
    @Column(name = "CHARACTER_LEVEL", nullable = false)
    private int characterLevel;
    @Column(name = "SKILL_ID", nullable = false)
    private int skillId;

    public CharacterSkillGrowthRecordPK() {
    }

    public CharacterSkillGrowthRecordPK(int characterId, int characterLevel, int skillId) {
        this.characterId = characterId;
        this.characterLevel = characterLevel;
        this.skillId = skillId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(int characterLevel) {
        this.characterLevel = characterLevel;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) characterId;
        hash += (int) characterLevel;
        hash += (int) skillId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterSkillGrowthRecordPK)) {
            return false;
        }
        CharacterSkillGrowthRecordPK other = (CharacterSkillGrowthRecordPK) object;
        if (this.characterId != other.characterId) {
            return false;
        }
        if (this.characterLevel != other.characterLevel) {
            return false;
        }
        if (this.skillId != other.skillId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterSkillGrowthRecordPK[characterId=" + characterId + ", characterLevel=" + characterLevel + ", skillId=" + skillId + "]";
    }

}
