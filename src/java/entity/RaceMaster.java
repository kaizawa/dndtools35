/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "RACE_MASTER")
@NamedQueries({@NamedQuery(name = "RaceMaster.findById", query = "SELECT r FROM RaceMaster r WHERE r.id = :id"), @NamedQuery(name = "RaceMaster.findByRaceName", query = "SELECT r FROM RaceMaster r WHERE r.raceName = :raceName"), @NamedQuery(name = "RaceMaster.findByDescription", query = "SELECT r FROM RaceMaster r WHERE r.description = :description")})
public class RaceMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "RACE_NAME")
    private String raceName;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceMaster")
    private Collection<RaceSaveMaster> raceSaveMasterCollection;
    @OneToMany(mappedBy = "raceId")
    private Collection<CharacterRecord> characterRecordCollection;
    @JoinColumn(name = "SIZE_ID", referencedColumnName = "ID")
    @ManyToOne
    private SizeMaster sizeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceMaster")
    private List<RaceAbilityMaster> raceAbilityMasterList;
    @Column(name = "SPEED")
    private Integer speed;

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public RaceMaster() {
    }

    public RaceMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<RaceSaveMaster> getRaceSaveMasterCollection() {
        return raceSaveMasterCollection;
    }

    public void setRaceSaveMasterCollection(Collection<RaceSaveMaster> raceSaveMasterCollection) {
        this.raceSaveMasterCollection = raceSaveMasterCollection;
    }

    public Collection<CharacterRecord> getCharacterRecordCollection() {
        return characterRecordCollection;
    }

    public void setCharacterRecordCollection(Collection<CharacterRecord> characterRecordCollection) {
        this.characterRecordCollection = characterRecordCollection;
    }

    public SizeMaster getSizeId() {
        return sizeId;
    }

    public void setSizeId(SizeMaster sizeId) {
        this.sizeId = sizeId;
    }

    public List<RaceAbilityMaster> getRaceAbilityMasterList() {
        return raceAbilityMasterList;
    }

    public void setRaceAbilityMasterList(List<RaceAbilityMaster> raceAbilityMasterList) {
        this.raceAbilityMasterList = raceAbilityMasterList;
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
        if (!(object instanceof RaceMaster)) {
            return false;
        }
        RaceMaster other = (RaceMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RaceMaster[id=" + id + "]";
    }

}
