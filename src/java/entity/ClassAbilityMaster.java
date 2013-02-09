/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "CLASS_ABILITY_MASTER")
@NamedQueries({@NamedQuery(name = "ClassAbilityMaster.findByClassId", query = "SELECT c FROM ClassAbilityMaster c WHERE c.classAbilityMasterPK.classId = :classId"), @NamedQuery(name = "ClassAbilityMaster.findByAbilityId", query = "SELECT c FROM ClassAbilityMaster c WHERE c.classAbilityMasterPK.abilityId = :abilityId")})
public class ClassAbilityMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClassAbilityMasterPK classAbilityMasterPK;
    @JoinColumn(name = "ABILITY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private AbilityMaster abilityMaster;
    @JoinColumn(name = "RANK_ID", referencedColumnName = "ID")
    @ManyToOne
    private BonusRankMaster rankId;
    @JoinColumn(name = "CLASS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private ClassMaster classMaster;

    public ClassAbilityMaster() {
    }

    public ClassAbilityMaster(ClassAbilityMasterPK classAbilityMasterPK) {
        this.classAbilityMasterPK = classAbilityMasterPK;
    }

    public ClassAbilityMaster(int classId, int abilityId) {
        this.classAbilityMasterPK = new ClassAbilityMasterPK(classId, abilityId);
    }

    public ClassAbilityMasterPK getClassAbilityMasterPK() {
        return classAbilityMasterPK;
    }

    public void setClassAbilityMasterPK(ClassAbilityMasterPK classAbilityMasterPK) {
        this.classAbilityMasterPK = classAbilityMasterPK;
    }

    public AbilityMaster getAbilityMaster() {
        return abilityMaster;
    }

    public void setAbilityMaster(AbilityMaster abilityMaster) {
        this.abilityMaster = abilityMaster;
    }

    public BonusRankMaster getRankId() {
        return rankId;
    }

    public void setRankId(BonusRankMaster rankId) {
        this.rankId = rankId;
    }

    public ClassMaster getClassMaster() {
        return classMaster;
    }

    public void setClassMaster(ClassMaster classMaster) {
        this.classMaster = classMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classAbilityMasterPK != null ? classAbilityMasterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassAbilityMaster)) {
            return false;
        }
        ClassAbilityMaster other = (ClassAbilityMaster) object;
        if ((this.classAbilityMasterPK == null && other.classAbilityMasterPK != null) || (this.classAbilityMasterPK != null && !this.classAbilityMasterPK.equals(other.classAbilityMasterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClassAbilityMaster[classAbilityMasterPK=" + classAbilityMasterPK + "]";
    }

}
