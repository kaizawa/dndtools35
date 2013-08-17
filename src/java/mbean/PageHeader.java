/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.AbilityMasterFacade;
import ejb.CampaignMasterFacade;
import ejb.CharacterAbilityRecordFacade;
import ejb.CharacterEquipmentFacade;
import ejb.CharacterRecordFacade;
import ejb.CharacterSaveRecordFacade;
import ejb.CharacterSkillRecordFacade;
import ejb.SaveMasterFacade;
import ejb.SkillMasterFacade;
import entity.*;
import java.util.List;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page fragment.
 * This class contains component definitions (and initialization code) for all
 * components that you have defined on this fragment, as well as lifecycle
 * methods and event handlers where you may add behavior to respond to incoming
 * events.</p>
 *
 * @version Header.java
 * @version Created on 2009/03/29, 14:55:38
 * @author ka78231
 */

@RequestScoped
public class PageHeader {

    @Inject
    private CharacterEquipmentFacade characterEquipmentFacade;
    @Inject
    private CharacterSaveRecordFacade characterSaveRecordFacade;
    @Inject
    private SaveMasterFacade saveMasterFacade;
    @Inject
    private CharacterAbilityRecordFacade characterAbilityRecordFacade;
    @Inject
    private AbilityMasterFacade abilityMasterFacade1;
    @Inject
    private AbilityMasterFacade abilityMasterFacade;
    @Inject
    private SkillMasterFacade skillMasterFacade;
    @Inject
    private CharacterSkillRecordFacade characterSkillRecordFacade;
    @Inject
    private CampaignMasterFacade campaignMasterFacade;
    @Inject
    private CharacterRecordFacade characterRecordFacade;

    public PageHeader() {
    }

    public String charaListLink_action() {
        return "CharacterListPage";
    }

    @Inject
    private SessionController sessionController;

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public String profileLink_action() {
        if (getSessionController().loggedIn) {
            return "profile";
        } else {
            return "LoginPage";
        }
    }

    public String adminLink_action() {
        if (getSessionController().loggedIn) {
            return "AdminPage";
        } else {
            return "LoginPage";
        }
    }

    public String loginLink_action() {
        getSessionController().setLoggedIn(false);
        getSessionController().setPlayerMaster(null);
        return "LoginPage";
    }

    public String logoutLink_action() {
        getSessionController().setLoggedIn(false);
        getSessionController().setPlayerMaster(null);
        return "LoginPage";
    }

    public String helpLink_action() {
        // TODO:アクションを処理します。戻り値は、
        //  ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }

    public String newClassLink_action() {
        if (getSessionController().loggedIn) {
            ClassMaster classMaster = new ClassMaster();
            getSessionController().setClassMaster(classMaster);
            return "EditClassPage";
        } else {
            return "LoginPage";
        }
    }

    public String classListLink_action() {
        if (getSessionController().loggedIn) {
            return "ClassListPage";
        } else {
            return "LoginPage";
        }
    }
    String selectedHandle;

    public void setSelectedHandle(String selectedHandle) {
        this.selectedHandle = selectedHandle;
    }

    public void raceDropDown_processValueChange(ValueChangeEvent vce) {
        // ドロップダウンを使った ページ遷移のサンプル.. ヘッダー(JSPF)では使えなかった。。。なぜかエラー
        //String handle = (String) raceDropDown.getValue();
        //moveToPage(handle);
    }

    public String newRaceLink_action() {
        if (getSessionController().loggedIn) {
            RaceMaster raceMaster = new RaceMaster();
            getSessionController().setRaceMaster(raceMaster);
            return "EditRacePage";
        } else {
            return "LoginPage";
        }
    }

    public String raceListLink_action() {
        if (getSessionController().loggedIn) {
            return "RaceListPage";
        } else {
            return "LoginPage";
        }
    }
}
