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
@Table(name = "ENCOUNTER_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncounterRecord.findAll", query = "SELECT e FROM EncounterRecord e"),
    @NamedQuery(name = "EncounterRecord.findById", query = "SELECT e FROM EncounterRecord e WHERE e.id = :id"),
    @NamedQuery(name = "EncounterRecord.findByComments", query = "SELECT e FROM EncounterRecord e WHERE e.comments = :comments"),
    @NamedQuery(name = "EncounterRecord.findByRound", query = "SELECT e FROM EncounterRecord e WHERE e.round = :round"),
    @NamedQuery(name = "EncounterRecord.findByTurn", query = "SELECT e FROM EncounterRecord e WHERE e.turn = :turn")})
public class EncounterRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "NAME", length = 255)
    private String name;    
    @Size(max = 4000)
    @Column(name = "COMMENTS", length = 4000)
    private String comments;
    @Column(name = "ROUND")
    private Integer round;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "TURN")
    private Integer turn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encounterRecord")
    private List<EncounterBattleMember> encounterBattleMemberList;

    public List<EncounterBattleMember> getEncounterBattleMemberList() {
        return encounterBattleMemberList;
    }

    public void setEncounterBattleMemberList(List<EncounterBattleMember> encounterBattleMemberList) {
        this.encounterBattleMemberList = encounterBattleMemberList;
    }

 

    public EncounterRecord() {
    }

    public EncounterRecord(Long id) {
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
        if (!(object instanceof EncounterRecord)) {
            return false;
        }
        EncounterRecord other = (EncounterRecord) object;
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
