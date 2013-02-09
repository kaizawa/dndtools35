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
public class CharacterGrowthRecordPK implements Serializable {
    @Column(name = "CHARACTER_ID", nullable = false)
    private int characterId;
    @Column(name = "CHARACTER_LEVEL", nullable = false)
    private int characterLevel;

    public CharacterGrowthRecordPK() {
    }

    public CharacterGrowthRecordPK(int characterId, int characterLevel) {
        this.characterId = characterId;
        this.characterLevel = characterLevel;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) characterId;
        hash += (int) characterLevel;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterGrowthRecordPK)) {
            return false;
        }
        CharacterGrowthRecordPK other = (CharacterGrowthRecordPK) object;
        if (this.characterId != other.characterId) {
            return false;
        }
        if (this.characterLevel != other.characterLevel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterGrowthRecordPK[characterId=" + characterId + ", characterLevel=" + characterLevel + "]";
    }

}
