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
@Table(name = "CAMPAIGN_MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CampaignMaster.findAll", query = "SELECT c FROM CampaignMaster c"),
    @NamedQuery(name = "CampaignMaster.findById", query = "SELECT c FROM CampaignMaster c WHERE c.id = :id"),
    @NamedQuery(name = "CampaignMaster.findByCampaignName", query = "SELECT c FROM CampaignMaster c WHERE c.campaignName = :campaignName"),
    @NamedQuery(name = "CampaignMaster.findByMaster", query = "SELECT c FROM CampaignMaster c WHERE c.master = :master")})
public class CampaignMaster implements Serializable {
    @OneToMany(mappedBy = "campaign")
    private List<ScenarioRecord> scenarioRecordList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "CAMPAIGN_NAME", length = 100)
    private String campaignName;
    @Size(max = 100)
    @Column(name = "MASTER", length = 100)
    private String master;

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
        return campaignName;
        //return "entity.CampaignMaster[ id=" + id + " ]";
    }

    @XmlTransient
    public List<ScenarioRecord> getScenarioRecordList() {
        return scenarioRecordList;
    }

    public void setScenarioRecordList(List<ScenarioRecord> scenarioRecordList) {
        this.scenarioRecordList = scenarioRecordList;
    }
    
}
