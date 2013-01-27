/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ka78231
 */
@Entity
@Table(name = "PLAYER_MASTER")
@NamedQueries({@NamedQuery(name = "PlayerMaster.findAll", query = "SELECT p FROM PlayerMaster p"), @NamedQuery(name = "PlayerMaster.findById", query = "SELECT p FROM PlayerMaster p WHERE p.id = :id"), @NamedQuery(name = "PlayerMaster.findByUsername", query = "SELECT p FROM PlayerMaster p WHERE p.username = :username"), @NamedQuery(name = "PlayerMaster.findByPassword", query = "SELECT p FROM PlayerMaster p WHERE p.password = :password"), @NamedQuery(name = "PlayerMaster.findByPlayerName", query = "SELECT p FROM PlayerMaster p WHERE p.playerName = :playerName")})
public class PlayerMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PLAYER_NAME")
    private String playerName;

    public PlayerMaster() {
    }

    public PlayerMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
        if (!(object instanceof PlayerMaster)) {
            return false;
        }
        PlayerMaster other = (PlayerMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PlayerMaster[id=" + id + "]";
    }

}
