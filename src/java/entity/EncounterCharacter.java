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
@Table(name = "ENCOUNTER_CHARACTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncounterCharacter.findAll", query = "SELECT e FROM EncounterCharacter e"),
    @NamedQuery(name = "EncounterCharacter.findById", query = "SELECT e FROM EncounterCharacter e WHERE e.id = :id"),
    @NamedQuery(name = "EncounterCharacter.findByplayerCharacter", query = "SELECT e FROM EncounterCharacter e WHERE e.playerCharacter = :playerCharacter"),
    @NamedQuery(name = "EncounterCharacter.findByEncounter", query = "SELECT e FROM EncounterCharacter e WHERE e.encounter = :encounter")})
public class EncounterCharacter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @JoinColumn(name = "PLAYER_CHARACTER", referencedColumnName = "ID")
    @ManyToOne
    private Playercharacter playerCharacter;
   
    @JoinColumn(name = "ENCOUNTER", referencedColumnName = "ID")
    @ManyToOne
    private Encounter encounter;

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Playercharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(Playercharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
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
        if (!(object instanceof EncounterCharacter)) {
            return false;
        }
        EncounterCharacter other = (EncounterCharacter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EncounterCharacter[ id=" + id + " ]";
    }
    
}
