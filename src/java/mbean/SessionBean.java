/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.PlayercharacterFacade;
import entity.EncounterRecord;
import entity.EncounterCharacter;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kaizawa
 */
@Stateful
@SessionScoped
public class SessionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private PlayercharacterFacade  playercharacterFacade;
    
    List<EncounterCharacter> playerCharacterList;    
    
    private EncounterRecord encounter;

    public EncounterRecord getEncounter() {
        return encounter;
    }

    public void setEncounter(EncounterRecord encounter) {
        this.encounter = encounter;
    }
    

    public List<EncounterCharacter> getPlayerCharacterList() {
        return playerCharacterList;
    }

    public void setPlayerCharacterList(List<EncounterCharacter> playerCharacterList) {
        this.playerCharacterList = playerCharacterList;
    }
}
