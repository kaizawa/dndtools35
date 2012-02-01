/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "CHARACTER_LEVEL_MASTER")
@NamedQueries({@NamedQuery(name = "CharacterLevelMaster.findByLevelNum", query = "SELECT c FROM CharacterLevelMaster c WHERE c.levelNum = :levelNum"), @NamedQuery(name = "CharacterLevelMaster.findByExpMin", query = "SELECT c FROM CharacterLevelMaster c WHERE c.expMin = :expMin"), @NamedQuery(name = "CharacterLevelMaster.findByExpMax", query = "SELECT c FROM CharacterLevelMaster c WHERE c.expMax = :expMax"), @NamedQuery(name = "CharacterLevelMaster.findBySkillRankMax", query = "SELECT c FROM CharacterLevelMaster c WHERE c.skillRankMax = :skillRankMax"), @NamedQuery(name = "CharacterLevelMaster.findByFeat", query = "SELECT c FROM CharacterLevelMaster c WHERE c.feat = :feat"), @NamedQuery(name = "CharacterLevelMaster.findByAbilityEnhancement", query = "SELECT c FROM CharacterLevelMaster c WHERE c.abilityEnhancement = :abilityEnhancement")})
public class CharacterLevelMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "LEVEL_NUM", nullable = false)
    private Integer levelNum;
    @Column(name = "EXP_MIN")
    private Integer expMin;
    @Column(name = "EXP_MAX")
    private Integer expMax;
    @Column(name = "SKILL_RANK_MAX")
    private Integer skillRankMax;
    @Column(name = "FEAT")
    private Integer feat;
    @Column(name = "ABILITY_ENHANCEMENT")
    private Integer abilityEnhancement;

    public CharacterLevelMaster() {
    }

    public CharacterLevelMaster(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public Integer getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public Integer getExpMin() {
        return expMin;
    }

    public void setExpMin(Integer expMin) {
        this.expMin = expMin;
    }

    public Integer getExpMax() {
        return expMax;
    }

    public void setExpMax(Integer expMax) {
        this.expMax = expMax;
    }

    public Integer getSkillRankMax() {
        return skillRankMax;
    }

    public void setSkillRankMax(Integer skillRankMax) {
        this.skillRankMax = skillRankMax;
    }

    public Integer getFeat() {
        return feat;
    }

    public void setFeat(Integer feat) {
        this.feat = feat;
    }

    public Integer getAbilityEnhancement() {
        return abilityEnhancement;
    }

    public void setAbilityEnhancement(Integer abilityEnhancement) {
        this.abilityEnhancement = abilityEnhancement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (levelNum != null ? levelNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharacterLevelMaster)) {
            return false;
        }
        CharacterLevelMaster other = (CharacterLevelMaster) object;
        if ((this.levelNum == null && other.levelNum != null) || (this.levelNum != null && !this.levelNum.equals(other.levelNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CharacterLevelMaster[levelNum=" + levelNum + "]";
    }

}
