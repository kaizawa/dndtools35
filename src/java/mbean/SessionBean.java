/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.PlayercharacterFacade;
import entity.Encounter;
import entity.Playercharacter;
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
    
    List<Playercharacter> playerCharacterList;    
    
    private Encounter encounter;

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }
    
    @PostConstruct
    public void init (){
        playerCharacterList = playercharacterFacade.findAll();
    }

    public List<Playercharacter> getPlayerCharacterList() {
        return playerCharacterList;
    }

    public void setPlayerCharacterList(List<Playercharacter> playerCharacterList) {
        this.playerCharacterList = playerCharacterList;
    }
}
