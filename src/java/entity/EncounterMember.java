/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
    @NamedQuery(name = "EncounterMember.findByInitiative", query = "SELECT e FROM EncounterMember e WHERE e.initiative = :initiative"),
    @NamedQuery(name = "EncounterMember.findByMyTurn", query = "SELECT e FROM EncounterMember e WHERE e.myTurn = :myTurn"),
    @NamedQuery(name = "EncounterMember.findByComments", query = "SELECT e FROM EncounterMember e WHERE e.comments = :comments")})
public class EncounterMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "INITIATIVE")
    private Integer initiative;
    @Column(name = "MY_TURN")
    private Short myTurn;
    @Size(max = 255)
    @Column(name = "COMMENTS", length = 255)
    private String comments;
    @JoinColumn(name = "SCENARIO_CHARACTER_RECORD", referencedColumnName = "ID")
    @ManyToOne
    private ScenarioCharacterRecord scenarioCharacterRecord;
    @JoinColumn(name = "ENCOUNTER_RECORD", referencedColumnName = "ID")
    @ManyToOne
    private EncounterRecord encounterRecord;

    public EncounterMember() {
    }

    public EncounterMember(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public Boolean getMyTurn() {
        if(myTurn != 0)
            return true;
        else
            return false;
    }

    public void setMyTurn(Boolean myTurn) {
        if(myTurn)
            this.myTurn = 1;
        else
            this.myTurn = 0;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ScenarioCharacterRecord getScenarioCharacterRecord() {
        return scenarioCharacterRecord;
    }

    public void setScenarioCharacterRecord(ScenarioCharacterRecord scenarioCharacterRecord) {
        this.scenarioCharacterRecord = scenarioCharacterRecord;
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
    
}
