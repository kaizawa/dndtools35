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
@Table(name = "MONSTER_FEAT_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsterFeatRecord.findAll", query = "SELECT m FROM MonsterFeatRecord m"),
    @NamedQuery(name = "MonsterFeatRecord.findByMonster", query = "SELECT m FROM MonsterFeatRecord m WHERE m.monsterFeatRecordPK.monster = :monster"),
    @NamedQuery(name = "MonsterFeatRecord.findByFeat", query = "SELECT m FROM MonsterFeatRecord m WHERE m.monsterFeatRecordPK.feat = :feat"),
    @NamedQuery(name = "MonsterFeatRecord.findByFeatModifier", query = "SELECT m FROM MonsterFeatRecord m WHERE m.featModifier = :featModifier"),
    @NamedQuery(name = "MonsterFeatRecord.findByDescription", query = "SELECT m FROM MonsterFeatRecord m WHERE m.description = :description")})
public class MonsterFeatRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MonsterFeatRecordPK monsterFeatRecordPK;
    @Column(name = "FEAT_MODIFIER")
    private Integer featModifier;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION", length = 4000)
    private String description;
    @JoinColumn(name = "MONSTER", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MonsterMaster monsterMaster;
    @JoinColumn(name = "FEAT", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FeatMaster featMaster;

    public MonsterFeatRecord() {
    }

    public MonsterFeatRecord(MonsterFeatRecordPK monsterFeatRecordPK) {
        this.monsterFeatRecordPK = monsterFeatRecordPK;
    }

    public MonsterFeatRecord(int monster, int feat) {
        this.monsterFeatRecordPK = new MonsterFeatRecordPK(monster, feat);
    }

    public MonsterFeatRecordPK getMonsterFeatRecordPK() {
        return monsterFeatRecordPK;
    }

    public void setMonsterFeatRecordPK(MonsterFeatRecordPK monsterFeatRecordPK) {
        this.monsterFeatRecordPK = monsterFeatRecordPK;
    }

    public Integer getFeatModifier() {
        return featModifier;
    }

    public void setFeatModifier(Integer featModifier) {
        this.featModifier = featModifier;
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

    public FeatMaster getFeatMaster() {
        return featMaster;
    }

    public void setFeatMaster(FeatMaster featMaster) {
        this.featMaster = featMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monsterFeatRecordPK != null ? monsterFeatRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterFeatRecord)) {
            return false;
        }
        MonsterFeatRecord other = (MonsterFeatRecord) object;
        if ((this.monsterFeatRecordPK == null && other.monsterFeatRecordPK != null) || (this.monsterFeatRecordPK != null && !this.monsterFeatRecordPK.equals(other.monsterFeatRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterFeatRecord[ monsterFeatRecordPK=" + monsterFeatRecordPK + " ]";
    }
    
}
