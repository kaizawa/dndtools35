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
@Table(name = "RACE_SAVE_MASTER")
@NamedQueries({@NamedQuery(name = "RaceSaveMaster.findByRaceId", query = "SELECT r FROM RaceSaveMaster r WHERE r.raceSaveMasterPK.raceId = :raceId"), @NamedQuery(name = "RaceSaveMaster.findBySaveId", query = "SELECT r FROM RaceSaveMaster r WHERE r.raceSaveMasterPK.saveId = :saveId"), @NamedQuery(name = "RaceSaveMaster.findByModifier", query = "SELECT r FROM RaceSaveMaster r WHERE r.modifier = :modifier")})
public class RaceSaveMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RaceSaveMasterPK raceSaveMasterPK;
    @Column(name = "MODIFIER")
    private Integer modifier;
    @JoinColumn(name = "RACE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private RaceMaster raceMaster;
    @JoinColumn(name = "SAVE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne
    private SaveMaster saveMaster;

    public RaceSaveMaster() {
    }

    public RaceSaveMaster(RaceSaveMasterPK raceSaveMasterPK) {
        this.raceSaveMasterPK = raceSaveMasterPK;
    }

    public RaceSaveMaster(int raceId, int saveId) {
        this.raceSaveMasterPK = new RaceSaveMasterPK(raceId, saveId);
    }

    public RaceSaveMasterPK getRaceSaveMasterPK() {
        return raceSaveMasterPK;
    }

    public void setRaceSaveMasterPK(RaceSaveMasterPK raceSaveMasterPK) {
        this.raceSaveMasterPK = raceSaveMasterPK;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public RaceMaster getRaceMaster() {
        return raceMaster;
    }

    public void setRaceMaster(RaceMaster raceMaster) {
        this.raceMaster = raceMaster;
    }

    public SaveMaster getSaveMaster() {
        return saveMaster;
    }

    public void setSaveMaster(SaveMaster saveMaster) {
        this.saveMaster = saveMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (raceSaveMasterPK != null ? raceSaveMasterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RaceSaveMaster)) {
            return false;
        }
        RaceSaveMaster other = (RaceSaveMaster) object;
        if ((this.raceSaveMasterPK == null && other.raceSaveMasterPK != null) || (this.raceSaveMasterPK != null && !this.raceSaveMasterPK.equals(other.raceSaveMasterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RaceSaveMaster[raceSaveMasterPK=" + raceSaveMasterPK + "]";
    }

}
