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
@Table(name = "MONSTER_SAVE_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsterSaveRecord.findAll", query = "SELECT m FROM MonsterSaveRecord m"),
    @NamedQuery(name = "MonsterSaveRecord.findByMonster", query = "SELECT m FROM MonsterSaveRecord m WHERE m.monsterSaveRecordPK.monster = :monster"),
    @NamedQuery(name = "MonsterSaveRecord.findByMonsterMaster", query = "SELECT m FROM MonsterSaveRecord m WHERE m.monsterMaster = :monster order by m.monsterSaveRecordPK.saveId"),
    @NamedQuery(name = "MonsterSaveRecord.findBySaveId", query = "SELECT m FROM MonsterSaveRecord m WHERE m.monsterSaveRecordPK.saveId = :saveId"),
    @NamedQuery(name = "MonsterSaveRecord.findByMiscModifier", query = "SELECT m FROM MonsterSaveRecord m WHERE m.miscModifier = :miscModifier"),
    @NamedQuery(name = "MonsterSaveRecord.findByDescription", query = "SELECT m FROM MonsterSaveRecord m WHERE m.description = :description")})
public class MonsterSaveRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MonsterSaveRecordPK monsterSaveRecordPK;
    @Column(name = "MISC_MODIFIER")
    private Integer miscModifier;
    @Size(max = 400)
    @Column(name = "DESCRIPTION", length = 400)
    private String description;
    @JoinColumn(name = "SAVE_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SaveMaster saveMaster;
    @JoinColumn(name = "MONSTER", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MonsterMaster monsterMaster;

    public MonsterSaveRecord() {
    }

    public MonsterSaveRecord(MonsterSaveRecordPK monsterSaveRecordPK) {
        this.monsterSaveRecordPK = monsterSaveRecordPK;
    }

    public MonsterSaveRecord(int monster, int saveId) {
        this.monsterSaveRecordPK = new MonsterSaveRecordPK(monster, saveId);
    }

    public MonsterSaveRecordPK getMonsterSaveRecordPK() {
        return monsterSaveRecordPK;
    }

    public void setMonsterSaveRecordPK(MonsterSaveRecordPK monsterSaveRecordPK) {
        this.monsterSaveRecordPK = monsterSaveRecordPK;
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

    public SaveMaster getSaveMaster() {
        return saveMaster;
    }

    public void setSaveMaster(SaveMaster saveMaster) {
        this.saveMaster = saveMaster;
    }

    public MonsterMaster getMonsterMaster() {
        return monsterMaster;
    }

    public void setMonsterMaster(MonsterMaster monsterMaster) {
        this.monsterMaster = monsterMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monsterSaveRecordPK != null ? monsterSaveRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterSaveRecord)) {
            return false;
        }
        MonsterSaveRecord other = (MonsterSaveRecord) object;
        if ((this.monsterSaveRecordPK == null && other.monsterSaveRecordPK != null) || (this.monsterSaveRecordPK != null && !this.monsterSaveRecordPK.equals(other.monsterSaveRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterSaveRecord[ monsterSaveRecordPK=" + monsterSaveRecordPK + " ]";
    }
    
}
