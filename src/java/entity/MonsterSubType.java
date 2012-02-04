/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "MONSTER_SUB_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsterSubType.findAll", query = "SELECT m FROM MonsterSubType m"),
    @NamedQuery(name = "MonsterSubType.findBySubType", query = "SELECT m FROM MonsterSubType m WHERE m.monsterSubTypePK.subType = :subType"),
    @NamedQuery(name = "MonsterSubType.findByMonster", query = "SELECT m FROM MonsterSubType m WHERE m.monsterSubTypePK.monster = :monster")})
public class MonsterSubType implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MonsterSubTypePK monsterSubTypePK;
    @JoinColumn(name = "MONSTER", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MonsterMaster monsterMaster;

    public MonsterSubType() {
    }

    public MonsterSubType(MonsterSubTypePK monsterSubTypePK) {
        this.monsterSubTypePK = monsterSubTypePK;
    }

    public MonsterSubType(int subType, int monster) {
        this.monsterSubTypePK = new MonsterSubTypePK(subType, monster);
    }

    public MonsterSubTypePK getMonsterSubTypePK() {
        return monsterSubTypePK;
    }

    public void setMonsterSubTypePK(MonsterSubTypePK monsterSubTypePK) {
        this.monsterSubTypePK = monsterSubTypePK;
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
        hash += (monsterSubTypePK != null ? monsterSubTypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterSubType)) {
            return false;
        }
        MonsterSubType other = (MonsterSubType) object;
        if ((this.monsterSubTypePK == null && other.monsterSubTypePK != null) || (this.monsterSubTypePK != null && !this.monsterSubTypePK.equals(other.monsterSubTypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonsterSubType[ monsterSubTypePK=" + monsterSubTypePK + " ]";
    }
    
}
