/*
 * SessionBean1.java
 *
 * Created on 2008/12/23, 23:35:51
 */
package mbean;

import ejb.*;
import entity.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 * <p>Session scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available across
 *  multiple HTTP requests for an individual user.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 *
 * @author ka78231
 */
@ManagedBean
@SessionScoped
public class SessionController {
    @EJB
    private CharacterRecordFacade characterRecordFacade;
    
    private Set selectedCharas = new LinkedHashSet();


    @EJB
    private BonusRankMasterFacade bonusRankMasterFacade;
    @EJB
    private ReligionMasterFacade religionMasterFacade;
    @EJB
    private GenderMasterFacade genderMasterFacade;
    @EJB
    private AlignmentMasterFacade alignmentMasterFacade;
    @EJB
    private AbilityMasterFacade abilityMasterFacade;
    @EJB
    private RaceMasterFacade raceMasterFacade;
    @EJB
    private CampaignMasterFacade campaignMasterFacade; 

    boolean loggedIn = false;
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
    }

    public boolean isNotLoggedIn(){
        return !loggedIn;
    }
}    
