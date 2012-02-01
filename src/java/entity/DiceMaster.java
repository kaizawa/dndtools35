/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "DICE_MASTER")
@NamedQueries({@NamedQuery(name = "DiceMaster.findById", query = "SELECT d FROM DiceMaster d WHERE d.id = :id"), @NamedQuery(name = "DiceMaster.findByName", query = "SELECT d FROM DiceMaster d WHERE d.name = :name"), @NamedQuery(name = "DiceMaster.findByType", query = "SELECT d FROM DiceMaster d WHERE d.type = :type")})
public class DiceMaster implements Serializable {
    @OneToMany(mappedBy = "hitDiceType")
    private List<ClassMaster> classMasterList;
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private Integer type;

    public DiceMaster() {
    }

    public DiceMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        if (!(object instanceof DiceMaster)) {
            return false;
        }
        DiceMaster other = (DiceMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DiceMaster[id=" + id + "]";
    }

    @XmlTransient
    public List<ClassMaster> getClassMasterList() {
        return classMasterList;
    }

    public void setClassMasterList(List<ClassMaster> classMasterList) {
        this.classMasterList = classMasterList;
    }

}
