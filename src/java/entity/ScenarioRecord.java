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
@Table(name = "SCENARIO_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScenarioRecord.findAll", query = "SELECT s FROM ScenarioRecord s"),
    @NamedQuery(name = "ScenarioRecord.findById", query = "SELECT s FROM ScenarioRecord s WHERE s.id = :id"),
    @NamedQuery(name = "ScenarioRecord.findByName", query = "SELECT s FROM ScenarioRecord s WHERE s.name = :name"),
    @NamedQuery(name = "ScenarioRecord.findByDescription", query = "SELECT s FROM ScenarioRecord s WHERE s.description = :description")})
public class ScenarioRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 400)
    @Column(name = "NAME", length = 400)
    private String name;
    @Size(max = 8000)
    @Column(name = "DESCRIPTION", length = 8000)
    private String description;
    @OneToMany(mappedBy = "scenario")
    private List<ScenarioCharacterRecord> scenarioCharacterRecordList;
    @JoinColumn(name = "CAMPAIGN", referencedColumnName = "ID")
    @ManyToOne
    private CampaignMaster campaign;

    public ScenarioRecord() {
    }

    public ScenarioRecord(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<ScenarioCharacterRecord> getScenarioCharacterRecordList() {
        return scenarioCharacterRecordList;
    }

    public void setScenarioCharacterRecordList(List<ScenarioCharacterRecord> scenarioCharacterRecordList) {
        this.scenarioCharacterRecordList = scenarioCharacterRecordList;
    }

    public CampaignMaster getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignMaster campaign) {
        this.campaign = campaign;
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
        if (!(object instanceof ScenarioRecord)) {
            return false;
        }
        ScenarioRecord other = (ScenarioRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ScenarioRecord[ id=" + id + " ]";
    }
    
}
