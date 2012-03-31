/*
 * SessionBean1.java
 *
 * Created on 2008/12/23, 23:35:51
 */
package mbean;

import ejb.*;
import entity.CharacterGrowthRecord;
import entity.ClassMaster;
import entity.PlayerMaster;
import entity.RaceMaster;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    
    /*
     * 選択されたキャンペーン
     */
    private Integer characterListSelectedCampaign = null;

    public Integer getCharacterListSelectedCampaign() {
        return characterListSelectedCampaign;
    }

    public void setCharacterListSelectedCampaign(Integer selectedCampaign) {
        this.characterListSelectedCampaign = selectedCampaign;
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
    

}    
