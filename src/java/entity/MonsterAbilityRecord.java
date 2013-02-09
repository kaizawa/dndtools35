/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "MONSTER_ABILITY_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsterAbilityRecord.findAll", query = "SELECT m FROM MonsterAbilityRecord m"),
    @NamedQuery(name = "MonsterAbilityRecord.findByMonster", query = "SELECT m FROM MonsterAbilityRecord m WHERE m.monsterAbilityRecordPK.monster = :monster"),
    @NamedQuery(name = "MonsterAbilityRecord.findByMonsterMaster", query = "SELECT m FROM MonsterAbilityRecord m WHERE m.monsterMaster = :monster order by m.monsterAbilityRecordPK.abilityId"),
    @NamedQuery(name = "MonsterAbilityRecord.findByAbilityId", query = "SELECT m FROM MonsterAbilityRecord m WHERE m.monsterAbilityRecordPK.abilityId = :abilityId"),
    @NamedQuery(name = "MonsterAbilityRecord.findByBase", query = "SELECT m FROM MonsterAbilityRecord m WHERE m.base = :base"),
    @NamedQuery(name = "MonsterAbilityRecord.findByFeatModifier", query = "SELECT m FROM MonsterAbilityRecord m WHERE m.featModifier = :featModifier"),
    @NamedQuery(name = "MonsterAbilityRecord.findByMiscModifier", query = "SELECT m FROM MonsterAbilityRecord m WHERE m.miscModifier = :miscModifier"),
    @NamedQuery(name = "MonsterAbilityRecord.findByDescription", query = "SELECT m FROM MonsterAbilityRecord m WHERE m.description = :description")})
public class MonsterAbilityRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MonsterAbilityRecordPK monsterAbilityRecordPK;
    @Column(name = "BASE")
    private Integer base = 0;
    @Column(name = "FEAT_MODIFIER")
    private Integer featModifier = 0;
    @Column(name = "MISC_MODIFIER")
    private Integer miscModifier = 0;
    @Size(max = 400)
    @Column(name = "DESCRIPTION", length = 400)
    private String description;
    @JoinColumn(name = "MONSTER", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MonsterMaster monsterMaster;
    @JoinColumn(name = "ABILITY_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AbilityMaster abilityMaster;

    public MonsterAbilityRecord() {
    }

    public MonsterAbilityRecord(MonsterAbilityRecordPK monsterAbilityRecordPK) {
        this.monsterAbilityRecordPK = monsterAbilityRecordPK;
    }

    public MonsterAbilityRecord(int monster, int abilityId) {
        this.monsterAbilityRecordPK = new MonsterAbilityRecordPK(monster, abilityId);
    }

    public MonsterAbilityRecordPK getMonsterAbilityRecordPK() {
        return monsterAbilityRecordPK;
    }

    public void setMonsterAbilityRecordPK(MonsterAbilityRecordPK monsterAbilityRecordPK) {
        this.monsterAbilityRecordPK = monsterAbilityRecordPK;
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
        hash += (monsterAbilityRecordPK != null ? monsterAbilityRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterAbilityRecord)) {
            return false;
        }
        MonsterAbilityRecord other = (MonsterAbilityRecord) object;
        if ((this.monsterAbilityRecordPK == null && other.monsterAbilityRecordPK != null) || (this.monsterAbilityRecordPK != null && !this.monsterAbilityRecordPK.equals(other.monsterAbilityRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterAbilityRecord[ monsterAbilityRecordPK=" + monsterAbilityRecordPK + " ]";
    }
    
}
