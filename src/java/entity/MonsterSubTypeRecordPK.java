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
public class MonsterSubTypeRecordPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUB_TYPE", nullable = false)
    private int subType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONSTER", nullable = false)
    private int monster;

    public MonsterSubTypeRecordPK() {
    }

    public MonsterSubTypeRecordPK(int subType, int monster) {
        this.subType = subType;
        this.monster = monster;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public int getMonster() {
        return monster;
    }

    public void setMonster(int monster) {
        this.monster = monster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) subType;
        hash += (int) monster;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterSubTypeRecordPK)) {
            return false;
        }
        MonsterSubTypeRecordPK other = (MonsterSubTypeRecordPK) object;
        if (this.subType != other.subType) {
            return false;
        }
        if (this.monster != other.monster) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterSubTypeRecordPK[ subType=" + subType + ", monster=" + monster + " ]";
    }
    
}
