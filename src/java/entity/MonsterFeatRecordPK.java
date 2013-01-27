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
public class MonsterFeatRecordPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONSTER", nullable = false)
    private int monster;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEAT", nullable = false)
    private int feat;

    public MonsterFeatRecordPK() {
    }

    public MonsterFeatRecordPK(int monster, int feat) {
        this.monster = monster;
        this.feat = feat;
    }

    public int getMonster() {
        return monster;
    }

    public void setMonster(int monster) {
        this.monster = monster;
    }

    public int getFeat() {
        return feat;
    }

    public void setFeat(int feat) {
        this.feat = feat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) monster;
        hash += (int) feat;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterFeatRecordPK)) {
            return false;
        }
        MonsterFeatRecordPK other = (MonsterFeatRecordPK) object;
        if (this.monster != other.monster) {
            return false;
        }
        if (this.feat != other.feat) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterFeatRecordPK[ monster=" + monster + ", feat=" + feat + " ]";
    }
    
}
