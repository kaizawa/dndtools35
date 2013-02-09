/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "BONUS_RANK_MASTER")
@NamedQueries({@NamedQuery(name = "BonusRankMaster.findById", query = "SELECT b FROM BonusRankMaster b WHERE b.id = :id"), @NamedQuery(name = "BonusRankMaster.findByRankName", query = "SELECT b FROM BonusRankMaster b WHERE b.rankName = :rankName")})
public class BonusRankMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "RANK_NAME")
    private String rankName;
    @OneToMany(mappedBy = "rankId")
    private Collection<ClassAbilityMaster> classAbilityMasterCollection;
    @OneToMany(mappedBy = "rankId")
    private Collection<ClassSaveMaster> classSaveMasterCollection;
    @OneToMany(mappedBy = "baseAttackRankId")
    private Collection<ClassMaster> classMasterCollection;

    public BonusRankMaster() {
    }

    public BonusRankMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public Collection<ClassAbilityMaster> getClassAbilityMasterCollection() {
        return classAbilityMasterCollection;
    }

    public void setClassAbilityMasterCollection(Collection<ClassAbilityMaster> classAbilityMasterCollection) {
        this.classAbilityMasterCollection = classAbilityMasterCollection;
    }

    public Collection<ClassSaveMaster> getClassSaveMasterCollection() {
        return classSaveMasterCollection;
    }

    public void setClassSaveMasterCollection(Collection<ClassSaveMaster> classSaveMasterCollection) {
        this.classSaveMasterCollection = classSaveMasterCollection;
    }

    public Collection<ClassMaster> getClassMasterCollection() {
        return classMasterCollection;
    }

    public void setClassMasterCollection(Collection<ClassMaster> classMasterCollection) {
        this.classMasterCollection = classMasterCollection;
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
        if (!(object instanceof BonusRankMaster)) {
            return false;
        }
        BonusRankMaster other = (BonusRankMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BonusRankMaster[id=" + id + "]";
    }

}
