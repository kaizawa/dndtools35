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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "GENDER_MASTER")
@NamedQueries({@NamedQuery(name = "GenderMaster.findById", query = "SELECT g FROM GenderMaster g WHERE g.id = :id"), @NamedQuery(name = "GenderMaster.findByGender", query = "SELECT g FROM GenderMaster g WHERE g.gender = :gender")})
public class GenderMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "GENDER")
    private String gender;
    @OneToMany(mappedBy = "genderId")
    private Collection<CharacterRecord> characterRecordCollection;

    public GenderMaster() {
    }

    public GenderMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Collection<CharacterRecord> getCharacterRecordCollection() {
        return characterRecordCollection;
    }

    public void setCharacterRecordCollection(Collection<CharacterRecord> characterRecordCollection) {
        this.characterRecordCollection = characterRecordCollection;
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
        if (!(object instanceof GenderMaster)) {
            return false;
        }
        GenderMaster other = (GenderMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GenderMaster[id=" + id + "]";
    }

}
