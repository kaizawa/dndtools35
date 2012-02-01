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
@Table(name = "CLASS_SKILL_MASTER")
@NamedQueries({@NamedQuery(name = "ClassSkillMaster.findByClassId", query = "SELECT c FROM ClassSkillMaster c WHERE c.classSkillMasterPK.classId = :classId"), @NamedQuery(name = "ClassSkillMaster.findBySkillId", query = "SELECT c FROM ClassSkillMaster c WHERE c.classSkillMasterPK.skillId = :skillId")})
public class ClassSkillMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClassSkillMasterPK classSkillMasterPK;
    @JoinColumn(name = "CLASS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private ClassMaster classMaster;

    public ClassSkillMaster() {
    }

    public ClassSkillMaster(ClassSkillMasterPK classSkillMasterPK) {
        this.classSkillMasterPK = classSkillMasterPK;
    }

    public ClassSkillMaster(int classId, int skillId) {
        this.classSkillMasterPK = new ClassSkillMasterPK(classId, skillId);
    }

    public ClassSkillMasterPK getClassSkillMasterPK() {
        return classSkillMasterPK;
    }

    public void setClassSkillMasterPK(ClassSkillMasterPK classSkillMasterPK) {
        this.classSkillMasterPK = classSkillMasterPK;
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
        hash += (classSkillMasterPK != null ? classSkillMasterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassSkillMaster)) {
            return false;
        }
        ClassSkillMaster other = (ClassSkillMaster) object;
        if ((this.classSkillMasterPK == null && other.classSkillMasterPK != null) || (this.classSkillMasterPK != null && !this.classSkillMasterPK.equals(other.classSkillMasterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClassSkillMaster[classSkillMasterPK=" + classSkillMasterPK + "]";
    }

}
