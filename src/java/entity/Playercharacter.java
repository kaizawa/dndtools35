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
@Table(name = "PLAYERCHARACTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Playercharacter.findAll", query = "SELECT p FROM Playercharacter p"),
    @NamedQuery(name = "Playercharacter.findById", query = "SELECT p FROM Playercharacter p WHERE p.id = :id"),
    @NamedQuery(name = "Playercharacter.findByComments", query = "SELECT p FROM Playercharacter p WHERE p.comments = :comments"),
    @NamedQuery(name = "Playercharacter.findByHitpoint", query = "SELECT p FROM Playercharacter p WHERE p.hitpoint = :hitpoint"),
    @NamedQuery(name = "Playercharacter.findByInitiative", query = "SELECT p FROM Playercharacter p WHERE p.initiative = :initiative"),
    @NamedQuery(name = "Playercharacter.findByKlass", query = "SELECT p FROM Playercharacter p WHERE p.klass = :klass"),
    @NamedQuery(name = "Playercharacter.findByName", query = "SELECT p FROM Playercharacter p WHERE p.name = :name")})
public class Playercharacter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Long id;
    @Size(max = 255)
    @Column(name = "COMMENTS", length = 255)
    private String comments;
    @Column(name = "HITPOINT")
    private Integer hitpoint;
    @Column(name = "INITIATIVE")
    private Integer initiative;
    @Size(max = 255)
    @Column(name = "KLASS", length = 255)
    private String klass;
    @Size(max = 255)
    @Column(name = "NAME", length = 255)
    private String name;

    public Playercharacter() {
    }

    public Playercharacter(Long id) {
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

    public Integer getHitpoint() {
        return hitpoint;
    }

    public void setHitpoint(Integer hitpoint) {
        this.hitpoint = hitpoint;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Playercharacter)) {
            return false;
        }
        Playercharacter other = (Playercharacter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Playercharacter[ id=" + id + " ]";
    }
    
}
