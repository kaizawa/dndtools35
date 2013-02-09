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
public class CharacterAbilityRecordPK implements Serializable {
    @Column(name = "CHARACTER_ID", nullable = false)
    private int characterId;
    @Column(name = "ABILITY_ID", nullable = false)
    private int abilityId;

    public CharacterAbilityRecordPK() {
    }

    public CharacterAbilityRecordPK(int characterId, int abilityId) {
        this.characterId = characterId;
        this.abilityId = abilityId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(int abilityId) {
        this.abilityId = abilityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) characterId;
        hash += (int) abilityId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterAbilityRecordPK)) {
            return false;
        }
        CharacterAbilityRecordPK other = (CharacterAbilityRecordPK) object;
        if (this.characterId != other.characterId) {
            return false;
        }
        if (this.abilityId != other.abilityId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterAbilityRecordPK[characterId=" + characterId + ", abilityId=" + abilityId + "]";
    }

}
