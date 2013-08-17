/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.entity;

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
public class MonsterSaveRecordPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONSTER", nullable = false)
    private int monster;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SAVE_ID", nullable = false)
    private int saveId;

    public MonsterSaveRecordPK() {
    }

    public MonsterSaveRecordPK(int monster, int saveId) {
        this.monster = monster;
        this.saveId = saveId;
    }

    public int getMonster() {
        return monster;
    }

    public void setMonster(int monster) {
        this.monster = monster;
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
        hash += (int) monster;
        hash += (int) saveId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterSaveRecordPK)) {
            return false;
        }
        MonsterSaveRecordPK other = (MonsterSaveRecordPK) object;
        if (this.monster != other.monster) {
            return false;
        }
        if (this.saveId != other.saveId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterSaveRecordPK[ monster=" + monster + ", saveId=" + saveId + " ]";
    }
    
}
