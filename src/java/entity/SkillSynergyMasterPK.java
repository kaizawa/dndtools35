/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ka78231
 */
@Embeddable
public class SkillSynergyMasterPK implements Serializable {
    @Column(name = "SKILL_ID", nullable = false)
    private int skillId;
    @Column(name = "AFFECTED_BY", nullable = false)
    private int affectedBy;

    public SkillSynergyMasterPK() {
    }

    public SkillSynergyMasterPK(int skillId, int affectedBy) {
        this.skillId = skillId;
        this.affectedBy = affectedBy;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getAffectedBy() {
        return affectedBy;
    }

    public void setAffectedBy(int affectedBy) {
        this.affectedBy = affectedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) skillId;
        hash += (int) affectedBy;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkillSynergyMasterPK)) {
            return false;
        }
        SkillSynergyMasterPK other = (SkillSynergyMasterPK) object;
        if (this.skillId != other.skillId) {
            return false;
        }
        if (this.affectedBy != other.affectedBy) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SkillSynergyMasterPK[skillId=" + skillId + ", affectedBy=" + affectedBy + "]";
    }

}
