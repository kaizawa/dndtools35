/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "RELIGION_MASTER")
@NamedQueries({@NamedQuery(name = "ReligionMaster.findById", query = "SELECT r FROM ReligionMaster r WHERE r.id = :id"), @NamedQuery(name = "ReligionMaster.findByReligionName", query = "SELECT r FROM ReligionMaster r WHERE r.religionName = :religionName"), @NamedQuery(name = "ReligionMaster.findByDescription", query = "SELECT r FROM ReligionMaster r WHERE r.description = :description")})
public class ReligionMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "RELIGION_NAME")
    private String religionName;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(mappedBy = "religionId")
    private Collection<CharacterRecord> characterRecordCollection;
    @JoinColumn(name = "ALIGNMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private AlignmentMaster alignmentId;

    public ReligionMaster() {
    }

    public ReligionMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReligionName() {
        return religionName;
    }

    public void setReligionName(String religionName) {
        this.religionName = religionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<CharacterRecord> getCharacterRecordCollection() {
        return characterRecordCollection;
    }

    public void setCharacterRecordCollection(Collection<CharacterRecord> characterRecordCollection) {
        this.characterRecordCollection = characterRecordCollection;
    }

    public AlignmentMaster getAlignmentId() {
        return alignmentId;
    }

    public void setAlignmentId(AlignmentMaster alignmentId) {
        this.alignmentId = alignmentId;
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
        if (!(object instanceof ReligionMaster)) {
            return false;
        }
        ReligionMaster other = (ReligionMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReligionMaster[id=" + id + "]";
    }

}
