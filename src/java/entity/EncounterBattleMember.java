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
@Table(name = "ENCOUNTER_BATTLE_MEMBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncounterBattleMember.findAll", query = "SELECT e FROM EncounterBattleMember e"),
    @NamedQuery(name = "EncounterBattleMember.findById", query = "SELECT e FROM EncounterBattleMember e WHERE e.id = :id"),
    @NamedQuery(name = "EncounterBattleMember.findByEncounterCharacter", query = "SELECT e FROM EncounterBattleMember e WHERE e.encounterCharacter = :encounterCharacter"),
    @NamedQuery(name = "EncounterBattleMember.findByEncounterRecord", query = "SELECT e FROM EncounterBattleMember e WHERE e.encounterRecord = :encounterRecord")})
public class EncounterBattleMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @JoinColumn(name = "ENCOUNTER_CHARACTER", referencedColumnName = "ID")
    @ManyToOne
    private EncounterCharacter encounterCharacter;
   
    @JoinColumn(name = "ENCOUNTER_RECORD", referencedColumnName = "ID")
    @ManyToOne
    private EncounterRecord encounterRecord;

    public EncounterCharacter getEncounterCharacter() {
        return encounterCharacter;
    }

    public void setEncounterCharacter(EncounterCharacter encounterCharacter) {
        this.encounterCharacter = encounterCharacter;
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
        if (!(object instanceof EncounterBattleMember)) {
            return false;
        }
        EncounterBattleMember other = (EncounterBattleMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EncounterBattleMember[ id=" + id + " ]";
    }
    
}
