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
@Table(name = "MONSTER_SUB_TYPE_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsterSubTypeRecord.findAll", query = "SELECT m FROM MonsterSubTypeRecord m"),
    @NamedQuery(name = "MonsterSubTypeRecord.findBySubType", query = "SELECT m FROM MonsterSubTypeRecord m WHERE m.monsterSubTypeRecordPK.subType = :subType"),
    @NamedQuery(name = "MonsterSubTypeRecord.findByMonster", query = "SELECT m FROM MonsterSubTypeRecord m WHERE m.monsterSubTypeRecordPK.monster = :monster"),
    @NamedQuery(name = "MonsterSubTypeRecord.findByDescription", query = "SELECT m FROM MonsterSubTypeRecord m WHERE m.description = :description")})
public class MonsterSubTypeRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MonsterSubTypeRecordPK monsterSubTypeRecordPK;
    @Size(max = 400)
    @Column(name = "DESCRIPTION", length = 400)
    private String description;
    @JoinColumn(name = "SUB_TYPE", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SubTypeMaster subTypeMaster;
    @JoinColumn(name = "MONSTER", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MonsterMaster monsterMaster;

    public MonsterSubTypeRecord() {
    }

    public MonsterSubTypeRecord(MonsterSubTypeRecordPK monsterSubTypeRecordPK) {
        this.monsterSubTypeRecordPK = monsterSubTypeRecordPK;
    }

    public MonsterSubTypeRecord(int subType, int monster) {
        this.monsterSubTypeRecordPK = new MonsterSubTypeRecordPK(subType, monster);
    }

    public MonsterSubTypeRecordPK getMonsterSubTypeRecordPK() {
        return monsterSubTypeRecordPK;
    }

    public void setMonsterSubTypeRecordPK(MonsterSubTypeRecordPK monsterSubTypeRecordPK) {
        this.monsterSubTypeRecordPK = monsterSubTypeRecordPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubTypeMaster getSubTypeMaster() {
        return subTypeMaster;
    }

    public void setSubTypeMaster(SubTypeMaster subTypeMaster) {
        this.subTypeMaster = subTypeMaster;
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
        hash += (monsterSubTypeRecordPK != null ? monsterSubTypeRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterSubTypeRecord)) {
            return false;
        }
        MonsterSubTypeRecord other = (MonsterSubTypeRecord) object;
        if ((this.monsterSubTypeRecordPK == null && other.monsterSubTypeRecordPK != null) || (this.monsterSubTypeRecordPK != null && !this.monsterSubTypeRecordPK.equals(other.monsterSubTypeRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterSubTypeRecord[ monsterSubTypeRecordPK=" + monsterSubTypeRecordPK + " ]";
    }
    
}
