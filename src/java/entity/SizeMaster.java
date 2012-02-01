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
@Table(name = "SIZE_MASTER")
@NamedQueries({@NamedQuery(name = "SizeMaster.findById", query = "SELECT s FROM SizeMaster s WHERE s.id = :id"), @NamedQuery(name = "SizeMaster.findBySizeName", query = "SELECT s FROM SizeMaster s WHERE s.sizeName = :sizeName"), @NamedQuery(name = "SizeMaster.findByAcModifier", query = "SELECT s FROM SizeMaster s WHERE s.acModifier = :acModifier")})
public class SizeMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "SIZE_NAME")
    private String sizeName;
    @Column(name = "AC_MODIFIER")
    private Integer acModifier;
    @OneToMany(mappedBy = "sizeId")
    private Collection<RaceMaster> raceMasterCollection;

    public SizeMaster() {
    }

    public SizeMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Integer getAcModifier() {
        return acModifier;
    }

    public void setAcModifier(Integer acModifier) {
        this.acModifier = acModifier;
    }

    public Collection<RaceMaster> getRaceMasterCollection() {
        return raceMasterCollection;
    }

    public void setRaceMasterCollection(Collection<RaceMaster> raceMasterCollection) {
        this.raceMasterCollection = raceMasterCollection;
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
        if (!(object instanceof SizeMaster)) {
            return false;
        }
        SizeMaster other = (SizeMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SizeMaster[id=" + id + "]";
    }

}
