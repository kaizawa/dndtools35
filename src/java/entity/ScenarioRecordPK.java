/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kaizawa
 */
@Embeddable
public class ScenarioRecordPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAMPAIGN", nullable = false)
    private int campaign;

    public ScenarioRecordPK() {
    }

    public ScenarioRecordPK(int id, int campaign) {
        this.id = id;
        this.campaign = campaign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCampaign() {
        return campaign;
    }

    public void setCampaign(int campaign) {
        this.campaign = campaign;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) campaign;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScenarioRecordPK)) {
            return false;
        }
        ScenarioRecordPK other = (ScenarioRecordPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.campaign != other.campaign) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ScenarioRecordPK[ id=" + id + ", campaign=" + campaign + " ]";
    }
    
}
