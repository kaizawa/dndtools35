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
public class CharacterSaveRecordPK implements Serializable {
    @Column(name = "CHARACTER_ID", nullable = false)
    private int characterId;
    @Column(name = "SAVE_ID", nullable = false)
    private int saveId;

    public CharacterSaveRecordPK() {
    }

    public CharacterSaveRecordPK(int characterId, int saveId) {
        this.characterId = characterId;
        this.saveId = saveId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getSaveId() {
        return saveId;
    }

    public void setSaveId(int saveId) {
        this.saveId = saveId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) characterId;
        hash += (int) saveId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterSaveRecordPK)) {
            return false;
        }
        CharacterSaveRecordPK other = (CharacterSaveRecordPK) object;
        if (this.characterId != other.characterId) {
            return false;
        }
        if (this.saveId != other.saveId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterSaveRecordPK[characterId=" + characterId + ", saveId=" + saveId + "]";
    }

}
