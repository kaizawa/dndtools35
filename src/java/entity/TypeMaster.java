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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kaizawa
 */
@Entity
@Table(name = "TYPE_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeMaster.findAll", query = "SELECT t FROM TypeMaster t"),
    @NamedQuery(name = "TypeMaster.findById", query = "SELECT t FROM TypeMaster t WHERE t.id = :id"),
    @NamedQuery(name = "TypeMaster.findByName", query = "SELECT t FROM TypeMaster t WHERE t.name = :name")})
public class TypeMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "NAME", length = 100)
    private String name;
    @OneToMany(mappedBy = "type")
    private List<MonsterMaster> monsterMasterList;

    public TypeMaster() {
    }

    public TypeMaster(Integer id) {
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

    @XmlTransient
    public List<MonsterMaster> getMonsterMasterList() {
        return monsterMasterList;
    }

    public void setMonsterMasterList(List<MonsterMaster> monsterMasterList) {
        this.monsterMasterList = monsterMasterList;
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
        if (!(object instanceof TypeMaster)) {
            return false;
        }
        TypeMaster other = (TypeMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "entity.TypeMaster[ id=" + id + " ]";
        return name;
    }
    
}
