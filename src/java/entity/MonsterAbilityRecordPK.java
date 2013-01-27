/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kaizawa
 */
@Embeddable
public class MonsterAbilityRecordPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONSTER", nullable = false)
    private int monster;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABILITY_ID", nullable = false)
    private int abilityId;

    public MonsterAbilityRecordPK() {
    }

    public MonsterAbilityRecordPK(int monster, int abilityId) {
        this.monster = monster;
        this.abilityId = abilityId;
    }

    public int getMonster() {
        return monster;
    }

    public void setMonster(int monster) {
        this.monster = monster;
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
        hash += (int) monster;
        hash += (int) abilityId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterAbilityRecordPK)) {
            return false;
        }
        MonsterAbilityRecordPK other = (MonsterAbilityRecordPK) object;
        if (this.monster != other.monster) {
            return false;
        }
        if (this.abilityId != other.abilityId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterAbilityRecordPK[ monster=" + monster + ", abilityId=" + abilityId + " ]";
    }
    
}
