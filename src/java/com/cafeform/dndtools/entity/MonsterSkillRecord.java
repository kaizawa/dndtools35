/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "MONSTER_SKILL_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsterSkillRecord.findAll", query = "SELECT m FROM MonsterSkillRecord m"),
    @NamedQuery(name = "MonsterSkillRecord.findByMonster", query = "SELECT m FROM MonsterSkillRecord m WHERE m.monsterSkillRecordPK.monster = :monster"),
    @NamedQuery(name = "MonsterSkillRecord.findByAbility", query = "SELECT m FROM MonsterSkillRecord m WHERE m.monsterSkillRecordPK.ability = :ability"),
    @NamedQuery(name = "MonsterSkillRecord.findByBase", query = "SELECT m FROM MonsterSkillRecord m WHERE m.base = :base"),
    @NamedQuery(name = "MonsterSkillRecord.findByFeatModifier", query = "SELECT m FROM MonsterSkillRecord m WHERE m.featModifier = :featModifier"),
    @NamedQuery(name = "MonsterSkillRecord.findByMiscModifier", query = "SELECT m FROM MonsterSkillRecord m WHERE m.miscModifier = :miscModifier"),
    @NamedQuery(name = "MonsterSkillRecord.findByDescription", query = "SELECT m FROM MonsterSkillRecord m WHERE m.description = :description")})
public class MonsterSkillRecord implements Serializable {
    @Column(name = "SKILL_MODIFIER")
    private Integer skillModifier;
    @JoinColumn(name = "SKILL", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SkillMaster skillMaster;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MonsterSkillRecordPK monsterSkillRecordPK;
    @Column(name = "BASE")
    private Integer base;
    @Column(name = "FEAT_MODIFIER")
    private Integer featModifier;
    @Column(name = "MISC_MODIFIER")
    private Integer miscModifier;
    @Size(max = 400)
    @Column(name = "DESCRIPTION", length = 400)
    private String description;
    @JoinColumn(name = "MONSTER", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MonsterMaster monsterMaster;
    @JoinColumn(name = "ABILITY", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AbilityMaster abilityMaster;

    public MonsterSkillRecord() {
    }

    public MonsterSkillRecord(MonsterSkillRecordPK monsterSkillRecordPK) {
        this.monsterSkillRecordPK = monsterSkillRecordPK;
    }

    public MonsterSkillRecord(int monster, int ability) {
        this.monsterSkillRecordPK = new MonsterSkillRecordPK(monster, ability);
    }

    public MonsterSkillRecordPK getMonsterSkillRecordPK() {
        return monsterSkillRecordPK;
    }

    public void setMonsterSkillRecordPK(MonsterSkillRecordPK monsterSkillRecordPK) {
        this.monsterSkillRecordPK = monsterSkillRecordPK;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public Integer getFeatModifier() {
        return featModifier;
    }

    public void setFeatModifier(Integer featModifier) {
        this.featModifier = featModifier;
    }

    public Integer getMiscModifier() {
        return miscModifier;
    }

    public void setMiscModifier(Integer miscModifier) {
        this.miscModifier = miscModifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MonsterMaster getMonsterMaster() {
        return monsterMaster;
    }

    public void setMonsterMaster(MonsterMaster monsterMaster) {
        this.monsterMaster = monsterMaster;
    }

    public AbilityMaster getAbilityMaster() {
        return abilityMaster;
    }

    public void setAbilityMaster(AbilityMaster abilityMaster) {
        this.abilityMaster = abilityMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monsterSkillRecordPK != null ? monsterSkillRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterSkillRecord)) {
            return false;
        }
        MonsterSkillRecord other = (MonsterSkillRecord) object;
        if ((this.monsterSkillRecordPK == null && other.monsterSkillRecordPK != null) || (this.monsterSkillRecordPK != null && !this.monsterSkillRecordPK.equals(other.monsterSkillRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterSkillRecord[ monsterSkillRecordPK=" + monsterSkillRecordPK + " ]";
    }

    public Integer getSkillModifier() {
        return skillModifier;
    }

    public void setSkillModifier(Integer skillModifier) {
        this.skillModifier = skillModifier;
    }

    public SkillMaster getSkillMaster() {
        return skillMaster;
    }

    public void setSkillMaster(SkillMaster skillMaster) {
        this.skillMaster = skillMaster;
    }
    
}
