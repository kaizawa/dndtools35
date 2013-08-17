/*
 * SessionBean1.java
 *
 * Created on 2008/12/23, 23:35:51
 */
package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.CharacterGrowthRecord;
import com.cafeform.dndtools.entity.CampaignMaster;
import com.cafeform.dndtools.entity.PlayerMaster;
import com.cafeform.dndtools.entity.ScenarioCharacterRecord;
import com.cafeform.dndtools.entity.ScenarioRecord;
import com.cafeform.dndtools.entity.ClassMaster;
import com.cafeform.dndtools.entity.RaceMaster;
import com.cafeform.dndtools.ejb.GenderMasterFacade;
import com.cafeform.dndtools.ejb.CharacterRecordFacade;
import com.cafeform.dndtools.ejb.BonusRankMasterFacade;
import com.cafeform.dndtools.ejb.AlignmentMasterFacade;
import com.cafeform.dndtools.ejb.AbilityMasterFacade;
import com.cafeform.dndtools.ejb.RaceMasterFacade;
import com.cafeform.dndtools.ejb.ReligionMasterFacade;
import com.cafeform.dndtools.ejb.CampaignMasterFacade;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class SessionController  implements Serializable {
    @Inject
    private CharacterRecordFacade characterRecordFacade;
    @Inject
    private BonusRankMasterFacade bonusRankMasterFacade;
    @Inject
    private ReligionMasterFacade religionMasterFacade;
    @Inject
    private GenderMasterFacade genderMasterFacade;
    @Inject
    private AlignmentMasterFacade alignmentMasterFacade;
    @Inject
    private AbilityMasterFacade abilityMasterFacade;
    @Inject
    private RaceMasterFacade raceMasterFacade;
    @Inject
    private CampaignMasterFacade campaignMasterFacade; 
    
    /** NOTE */
    /*
    Need to remove all javajavax.faces.bean.ManagedBean and @ManagedProperty.
            javax.faces.bean.ManagedBean need to be replaced by javax.annotation.ManagedBean;
            and @ManagedProperty must be replaced by @Inject
                    @Inject can is also be replaced by @Inject
                    * */
    
    @Inject
    private ApplicationController applicationController;

    public ApplicationController getApplicationController() {
        return applicationController;
    }

    public void setApplicationController(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }
    
    @PostConstruct
    public void init() {
        setCheckedCharacterMap(new HashMap());   
        setLoggedIn(false);
    }    

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
    
    /*
     * キャラクターデータ
     */
    private CharacterData characterData;

    public CharacterData getCharacterData() {
        return characterData;
    }

    public void setCharacterData(CharacterData characterData) {
        this.characterData = characterData;
    }
    
    /*
     * キャラクタ成長レコード
     */
    protected CharacterGrowthRecord characterGrowthRecord;

    public CharacterGrowthRecord getCharacterGrowthRecord() {
        return characterGrowthRecord;
    }

    public void setCharacterGrowthRecord(CharacterGrowthRecord characterGrowthRecord) {
        this.characterGrowthRecord = characterGrowthRecord;
    }    

    public Boolean isCampaignSelected(){
        return !(selectedCampaign == null);
    }
    
    /*
     * チェックボックスで選択されたキャラクターのセット
     */
    private Map checkedCharacterMap;


    public Map getCheckedCharacterMap() {
        return checkedCharacterMap;
    }

    public void setCheckedCharacterMap(Map selectedCharas) {
        this.checkedCharacterMap = selectedCharas;
    }

    /*
     * プレイヤー(ログインユーザ)
     */
    protected PlayerMaster playerMaster;

    public PlayerMaster getPlayerMaster() {
        return playerMaster;
    }

    public void setPlayerMaster(PlayerMaster playerMaster) {
        this.playerMaster = playerMaster;
    }
    
    /*
     * クラス設定ページ用。編集しているクラス
     */
    private ClassMaster classMaster;
    public ClassMaster getClassMaster() {
        return classMaster;
    }
    public void setClassMaster(ClassMaster classMaster) {
        this.classMaster = classMaster;
    }
 
    /*
     * 種族設定ページ用。
     */
    protected RaceMaster raceMaster;

    public RaceMaster getRaceMaster() {
        return raceMaster;
    }

    public void setRaceMaster(RaceMaster raceMaster) {
        this.raceMaster = raceMaster;
    }

    private String targetPage; 
    
    public void setTargetPage(String targetPage) {
        this.targetPage = targetPage;
    }
    
    public String getTargetPage(){
        return this.targetPage;
    }
    
    private ScenarioCharacterRecord scenarioCharacterRecord;

    public ScenarioCharacterRecord getScenarioCharacterRecord() {
        return scenarioCharacterRecord;
    }

    public void setScenarioCharacterRecord(ScenarioCharacterRecord scenarioCharacterRecord) {
        this.scenarioCharacterRecord = scenarioCharacterRecord;
    }
    
    private ScenarioRecord selectedScenarioRecord = null;

    public ScenarioRecord getSelectedScenarioRecord() {
        return selectedScenarioRecord;
    }

    public void setSelectedScenarioRecord(ScenarioRecord scenarioRecord) {
        this.selectedScenarioRecord = scenarioRecord;
    }
    
    public Boolean isScenarioSelected(){
        return ! (getSelectedScenarioRecord() == null);
    }
    
    public String goToLimitedPage(String pageName){
        if (isLoggedIn() == false) {
            setTargetPage(pageName);
            return "/login/LoginPage";
        }
        return pageName;
    }
    
    
    /*
     * 選択されたキャンペーン
     */        
    private CampaignMaster selectedCampaign;

    CampaignMaster getSelectedCampaign() {
        return selectedCampaign;
    }

    void setSelectedCampaign(CampaignMaster selectedCampaign) {
        this.selectedCampaign = selectedCampaign;
    }
}    
