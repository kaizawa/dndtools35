/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "ENCOUNTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encounter.findAll", query = "SELECT e FROM Encounter e"),
    @NamedQuery(name = "Encounter.findById", query = "SELECT e FROM Encounter e WHERE e.id = :id"),
    @NamedQuery(name = "Encounter.findByComments", query = "SELECT e FROM Encounter e WHERE e.comments = :comments"),
    @NamedQuery(name = "Encounter.findByRound", query = "SELECT e FROM Encounter e WHERE e.round = :round"),
    @NamedQuery(name = "Encounter.findByTurn", query = "SELECT e FROM Encounter e WHERE e.turn = :turn")})
public class Encounter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Long id;
    @Size(max = 255)
    @Column(name = "COMMENTS", length = 255)
    private String comments;
    @Column(name = "ROUND")
    private Integer round;
    @Column(name = "TURN")
    private Integer turn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encounter")
    private List<EncounterCharacter> encounterCharacterList;

    public List<EncounterCharacter> getEncounterCharacterList() {
        return encounterCharacterList;
    }

    public void setEncounterCharacterList(List<EncounterCharacter> encounterCharacterList) {
        this.encounterCharacterList = encounterCharacterList;
    }

    public Encounter() {
    }

    public Encounter(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
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
        if (!(object instanceof Encounter)) {
            return false;
        }
        Encounter other = (Encounter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Encounter[ id=" + id + " ]";
    }
    
}
