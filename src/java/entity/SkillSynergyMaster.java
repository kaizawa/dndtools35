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
@Table(name = "SKILL_SYNERGY_MASTER")
@NamedQueries({@NamedQuery(name = "SkillSynergyMaster.findBySkillId", query = "SELECT s FROM SkillSynergyMaster s WHERE s.skillSynergyMasterPK.skillId = :skillId"), @NamedQuery(name = "SkillSynergyMaster.findByAffectedBy", query = "SELECT s FROM SkillSynergyMaster s WHERE s.skillSynergyMasterPK.affectedBy = :affectedBy")})
public class SkillSynergyMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SkillSynergyMasterPK skillSynergyMasterPK;
    @JoinColumn(name = "SKILL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private SkillMaster skillMaster;

    public SkillSynergyMaster() {
    }

    public SkillSynergyMaster(SkillSynergyMasterPK skillSynergyMasterPK) {
        this.skillSynergyMasterPK = skillSynergyMasterPK;
    }

    public SkillSynergyMaster(int skillId, int affectedBy) {
        this.skillSynergyMasterPK = new SkillSynergyMasterPK(skillId, affectedBy);
    }

    public SkillSynergyMasterPK getSkillSynergyMasterPK() {
        return skillSynergyMasterPK;
    }

    public void setSkillSynergyMasterPK(SkillSynergyMasterPK skillSynergyMasterPK) {
        this.skillSynergyMasterPK = skillSynergyMasterPK;
    }

    public SkillMaster getSkillMaster() {
        return skillMaster;
    }

    public void setSkillMaster(SkillMaster skillMaster) {
        this.skillMaster = skillMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillSynergyMasterPK != null ? skillSynergyMasterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkillSynergyMaster)) {
            return false;
        }
        SkillSynergyMaster other = (SkillSynergyMaster) object;
        if ((this.skillSynergyMasterPK == null && other.skillSynergyMasterPK != null) || (this.skillSynergyMasterPK != null && !this.skillSynergyMasterPK.equals(other.skillSynergyMasterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SkillSynergyMaster[skillSynergyMasterPK=" + skillSynergyMasterPK + "]";
    }

}
