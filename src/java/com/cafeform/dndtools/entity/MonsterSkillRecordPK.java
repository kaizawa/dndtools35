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
public class MonsterSkillRecordPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONSTER", nullable = false)
    private int monster;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABILITY", nullable = false)
    private int ability;

    public MonsterSkillRecordPK() {
    }

    public MonsterSkillRecordPK(int monster, int ability) {
        this.monster = monster;
        this.ability = ability;
    }

    public int getMonster() {
        return monster;
    }

    public void setMonster(int monster) {
        this.monster = monster;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) monster;
        hash += (int) ability;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterSkillRecordPK)) {
            return false;
        }
        MonsterSkillRecordPK other = (MonsterSkillRecordPK) object;
        if (this.monster != other.monster) {
            return false;
        }
        if (this.ability != other.ability) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterSkillRecordPK[ monster=" + monster + ", ability=" + ability + " ]";
    }
    
}
