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
@Table(name = "CAMPAIGN_MASTER")
@NamedQueries({@NamedQuery(name = "CampaignMaster.findById", query = "SELECT c FROM CampaignMaster c WHERE c.id = :id"), @NamedQuery(name = "CampaignMaster.findByCampaignName", query = "SELECT c FROM CampaignMaster c WHERE c.campaignName = :campaignName"), @NamedQuery(name = "CampaignMaster.findByMaster", query = "SELECT c FROM CampaignMaster c WHERE c.master = :master")})
public class CampaignMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "CAMPAIGN_NAME")
    private String campaignName;
    @Column(name = "MASTER")
    private String master;
    @OneToMany(mappedBy = "campaignId")
    private Collection<CharacterRecord> characterRecordCollection;

    public CampaignMaster() {
    }

    public CampaignMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
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
        if (!(object instanceof CampaignMaster)) {
            return false;
        }
        CampaignMaster other = (CampaignMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CampaignMaster[id=" + id + "]";
    }

}
