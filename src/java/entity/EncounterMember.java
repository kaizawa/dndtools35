/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.ScenarioCharacterRecord;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "ENCOUNTER_MEMBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncounterMember.findAll", query = "SELECT e FROM EncounterMember e"),
    @NamedQuery(name = "EncounterMember.findById", query = "SELECT e FROM EncounterMember e WHERE e.id = :id"),
    @NamedQuery(name = "EncounterMember.findByEncounterCharacterAndEncounterRecord", query = "SELECT e FROM EncounterMember e WHERE e.encounterCharacter = :encounterCharacter AND e.encounterRecord = :encounterRecord"),
    @NamedQuery(name = "EncounterMember.findByEncounterCharacter", query = "SELECT e FROM EncounterMember e WHERE e.encounterCharacter = :encounterCharacter"),
    @NamedQuery(name = "EncounterMember.findByEncounterRecord", query = "SELECT e FROM EncounterMember e WHERE e.encounterRecord = :encounterRecord")})
public class EncounterMember implements Serializable {
    @Column(name = "MY_TURN")
    private Short myTurn;
    @JoinColumn(name = "SCENARIO_CHARACTER_RECORD", referencedColumnName = "ID")
    @ManyToOne
    private ScenarioCharacterRecord scenarioCharacterRecord;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "ENCOUNTER_RECORD", referencedColumnName = "ID")
    @ManyToOne
    private EncounterRecord encounterRecord;
    @Column(name = "HITPOINT")
    private Integer hitPoint = 0;
    @Column(name = "INITIATIVE")
    private Integer initiative = 0;
    @Size(max = 255)
    @Column(name = "COMMENTS", length = 255)
    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getMyTurn() {
        return (myTurn != 0);
    }

    public void setMyTurn(Boolean myTurn) {
        if(myTurn)
            this.myTurn = 1;
        else 
            this.myTurn = 0;
    }

    public Integer getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(Integer hitPoint) {
        this.hitPoint = hitPoint;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EncounterRecord getEncounterRecord() {
        return encounterRecord;
    }

    public void setEncounterRecord(EncounterRecord encounterRecord) {
        this.encounterRecord = encounterRecord;
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
        if (!(object instanceof EncounterMember)) {
            return false;
        }
        EncounterMember other = (EncounterMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EncounterMember[ id=" + id + " ]";
    }

    public EncounterMember() {
    }

    public ScenarioCharacterRecord getScenarioCharacterRecord() {
        return scenarioCharacterRecord;
    }

    public void setScenarioCharacterRecord(ScenarioCharacterRecord scenarioCharacterRecord) {
        this.scenarioCharacterRecord = scenarioCharacterRecord;
    }
}
