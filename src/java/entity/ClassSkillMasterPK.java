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
public class ClassSkillMasterPK implements Serializable {
    @Column(name = "CLASS_ID", nullable = false)
    private int classId;
    @Column(name = "SKILL_ID", nullable = false)
    private int skillId;

    public ClassSkillMasterPK() {
    }

    public ClassSkillMasterPK(int classId, int skillId) {
        this.classId = classId;
        this.skillId = skillId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) classId;
        hash += (int) skillId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassSkillMasterPK)) {
            return false;
        }
        ClassSkillMasterPK other = (ClassSkillMasterPK) object;
        if (this.classId != other.classId) {
            return false;
        }
        if (this.skillId != other.skillId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClassSkillMasterPK[classId=" + classId + ", skillId=" + skillId + "]";
    }

}
