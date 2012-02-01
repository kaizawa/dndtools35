/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "RACE_ABILITY_MASTER")
@NamedQueries({@NamedQuery(name = "RaceAbilityMaster.findByRaceId", query = "SELECT r FROM RaceAbilityMaster r WHERE r.raceAbilityMasterPK.raceId = :raceId"), @NamedQuery(name = "RaceAbilityMaster.findByAbilityId", query = "SELECT r FROM RaceAbilityMaster r WHERE r.raceAbilityMasterPK.abilityId = :abilityId"), @NamedQuery(name = "RaceAbilityMaster.findByModifier", query = "SELECT r FROM RaceAbilityMaster r WHERE r.modifier = :modifier")})
public class RaceAbilityMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RaceAbilityMasterPK raceAbilityMasterPK;
    @Column(name = "MODIFIER")
    private Integer modifier;
    @JoinColumn(name = "ABILITY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private AbilityMaster abilityMaster;
    @JoinColumn(name = "RACE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private RaceMaster raceMaster;

    public RaceAbilityMaster() {
    }

    public RaceAbilityMaster(RaceAbilityMasterPK raceAbilityMasterPK) {
        this.raceAbilityMasterPK = raceAbilityMasterPK;
    }

    public RaceAbilityMaster(int raceId, int abilityId) {
        this.raceAbilityMasterPK = new RaceAbilityMasterPK(raceId, abilityId);
    }

    public RaceAbilityMasterPK getRaceAbilityMasterPK() {
        return raceAbilityMasterPK;
    }

    public void setRaceAbilityMasterPK(RaceAbilityMasterPK raceAbilityMasterPK) {
        this.raceAbilityMasterPK = raceAbilityMasterPK;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public AbilityMaster getAbilityMaster() {
        return abilityMaster;
    }

    public void setAbilityMaster(AbilityMaster abilityMaster) {
        this.abilityMaster = abilityMaster;
    }

    public RaceMaster getRaceMaster() {
        return raceMaster;
    }

    public void setRaceMaster(RaceMaster raceMaster) {
        this.raceMaster = raceMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (raceAbilityMasterPK != null ? raceAbilityMasterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RaceAbilityMaster)) {
            return false;
        }
        RaceAbilityMaster other = (RaceAbilityMaster) object;
        if ((this.raceAbilityMasterPK == null && other.raceAbilityMasterPK != null) || (this.raceAbilityMasterPK != null && !this.raceAbilityMasterPK.equals(other.raceAbilityMasterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RaceAbilityMaster[raceAbilityMasterPK=" + raceAbilityMasterPK + "]";
    }

}
