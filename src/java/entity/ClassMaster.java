/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "CLASS_MASTER")
@NamedQueries({@NamedQuery(name = "ClassMaster.findById", query = "SELECT c FROM ClassMaster c WHERE c.id = :id"), @NamedQuery(name = "ClassMaster.findByClassName", query = "SELECT c FROM ClassMaster c WHERE c.className = :className"), @NamedQuery(name = "ClassMaster.findByDescription", query = "SELECT c FROM ClassMaster c WHERE c.description = :description")})
public class ClassMaster implements Serializable {
    @OneToMany(mappedBy = "classId")
    private List<CharacterGrowthRecord> characterGrowthRecordList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "CLASS_NAME")
    private String className;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "BASE_ATTACK_RANK_ID", referencedColumnName = "ID")
    @ManyToOne
    private BonusRankMaster baseAttackRankId;
    @JoinColumn(name = "HIT_DICE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private DiceMaster hitDiceType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classMaster")
    private List<ClassAbilityMaster> classAbilityMasterList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classMaster")
    private List<ClassSaveMaster> classSaveMasterList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classMaster")
    private List<ClassSkillMaster> classSkillMasterList;
    @Column(name = "SKILL_POINT")
    private Integer skillPoint;

    public Integer getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(Integer skillPoint) {
        this.skillPoint = skillPoint;
    }

    public List<ClassAbilityMaster> getClassAbilityMasterList() {
        return classAbilityMasterList;
    }

    public void setClassAbilityMasterList(List<ClassAbilityMaster> classAbilityMasterList) {
        this.classAbilityMasterList = classAbilityMasterList;
    }

    public List<ClassSaveMaster> getClassSaveMasterList() {
        return classSaveMasterList;
    }

    public void setClassSaveMasterList(List<ClassSaveMaster> classSaveMasterList) {
        this.classSaveMasterList = classSaveMasterList;
    }

    public List<ClassSkillMaster> getClassSkillMasterList() {
        return classSkillMasterList;
    }

    public void setClassSkillMasterList(List<ClassSkillMaster> classSkillMasterList) {
        this.classSkillMasterList = classSkillMasterList;
    }

    public ClassMaster() {
    }

    public ClassMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BonusRankMaster getBaseAttackRankId() {
        return baseAttackRankId;
    }

    public void setBaseAttackRankId(BonusRankMaster baseAttackRankId) {
        this.baseAttackRankId = baseAttackRankId;
    }

    public DiceMaster getHitDiceType() {
        return hitDiceType;
    }

    public void setHitDiceType(DiceMaster hitDiceType) {
        this.hitDiceType = hitDiceType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassMaster)) {
            return false;
        }
        ClassMaster other = (ClassMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClassMaster[id=" + id + "]";
    }

    @XmlTransient
    public List<CharacterGrowthRecord> getCharacterGrowthRecordList() {
        return characterGrowthRecordList;
    }

    public void setCharacterGrowthRecordList(List<CharacterGrowthRecord> characterGrowthRecordList) {
        this.characterGrowthRecordList = characterGrowthRecordList;
    }

}
