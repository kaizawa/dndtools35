/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.*;
import entity.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import mbean.util.JsfUtil;

/**
 *
 * @author kaizawa
 */
@ManagedBean(name = "characterSheetController")
@SessionScoped
public class CharacterSheetController implements Serializable {

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
    @EJB
    private CharacterSkillGrowthRecordFacade characterSkillGrowthRecordFacade;
    @EJB
    private CharacterGrowthRecordFacade characterGrowthRecordFacade;
    @EJB
    private CharacterEquipmentFacade characterEquipmentFacade;
    @EJB
    private CharacterSaveRecordFacade characterSaveRecordFacade;
    @EJB
    private SaveMasterFacade saveMasterFacade;
    @EJB
    private CharacterAbilityRecordFacade characterAbilityRecordFacade;
    @EJB
    private SkillMasterFacade skillMasterFacade;
    @EJB
    private CharacterSkillRecordFacade characterSkillRecordFacade;
    boolean loggedIn = false;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isNotLoggedIn() {
        return !loggedIn;
    }
    protected PlayerMaster playerMaster;

    /**
     * Get the value of playerMaster
     *
     * @return the value of playerMaster
     */
    public PlayerMaster getPlayerMaster() {
        return playerMaster;
    }

    /**
     * Set the value of playerMaster
     *
     * @param playerMaster new value of playerMaster
     */
    public void setPlayerMaster(PlayerMaster playerMaster) {
        this.playerMaster = playerMaster;
    }

    protected ApplicationBean getApplicationBean() {
        return getApplicationBean();
    }
    // 選択されたキャンペーン
    private Integer characterListSelectedCampaign = null;

    public Integer getCharacterListSelectedCampaign() {
        return characterListSelectedCampaign;
    }

    public void setCharacterListSelectedCampaign(Integer selectedCampaign) {
        this.characterListSelectedCampaign = selectedCampaign;
    }
    ///////////////////////////////
    // キャラクターデータのリスト
    //////////////////////////////////
    public List<CharacterData> getCharacterDataList() {

        List<CharacterData> charaDataList = new ArrayList<CharacterData>();
        if (getCharacterListSelectedCampaign() == null) {
            for(CharacterRecord charaRecord : characterRecordFacade.findAll()){
                charaDataList.add(new CharacterData(charaRecord));
            }
        } else {
            //選択されたキャンペーンキャラクターレコードのリストを得る
            for(CharacterRecord charaRecord :  characterRecordFacade.findByCampaignId(getCharacterListSelectedCampaign())){
                charaDataList.add(new CharacterData(charaRecord));                
            }
        }
        return charaDataList;
    }

    // キャラクターデータ
    private CharacterData characterData;

    public CharacterData getCharacterData() {
        return characterData;
    }

    public void setCharacterData(CharacterData characterData) {
        this.characterData = characterData;
    }


    // キャラクタの削除ボタンを有効比するかどうかの判定。使ってないか？
    private boolean deleteButtonDisabled;

    public boolean isDeleteButtonDisabled() {
        if (this.characterData.getId() == null) {
            return true;
        } else {
            return false;
        }
    }

    public void setDeleteButtonDisabled(boolean deleteButtonDisabled) {
        this.deleteButtonDisabled = deleteButtonDisabled;
    }
    // キャンペーンの選択が必要？   使ってる？
    private boolean campaignSelectRequired;

    public boolean isCampaignSelectRequired() {
        return campaignSelectRequired;
    }

    public void setCampaignSelectRequired(boolean campaignSelectRequired) {
        this.campaignSelectRequired = campaignSelectRequired;
    }
    // キャラクタの成長レコードのリスト
    private List<CharacterGrowthRecord> characterGrowthRecordList;

    public List<CharacterGrowthRecord> getCharacterGrowthRecordList() {
        return characterGrowthRecordList;
    }

    public void setCharacterGrowthRecordList(List<CharacterGrowthRecord> characterGrowthRecordList) {
        this.characterGrowthRecordList = characterGrowthRecordList;
    }
    // キャラクタの技能成長レコードのリスト
    private List<CharacterSkillGrowthRecord> characterSkillGrowthRecordList;

    public List<CharacterSkillGrowthRecord> getCharacterSkillGrowthRecordList() {
        return characterSkillGrowthRecordList;
    }

    public void setCharacterSkillGrowthRecordList(List<CharacterSkillGrowthRecord> characterSkillGrowthRecordList) {
        this.characterSkillGrowthRecordList = characterSkillGrowthRecordList;
    }
    //キャンペーンの選択  
    // このプロパティはページ間でやり取りされるので、セッションBeanでよい
    Integer selectedCampaign;

    public Integer getSelectedCampaign() {
        if (this.characterData.getCampaignId() == null) {
            return null;
        }
        return this.characterData.getCampaignId().getId();
    }

    public void setSelectedCampaign(Integer campaignId) {
        if (campaignId == null) {
            this.characterData.setCampaignId(null);
            return;
        }
        CampaignMaster campaign = campaignMasterFacade.find(campaignId);
        this.characterData.setCampaignId(campaign);
    }
    //  選択された属性
    Integer selectedAlignment;

    public Integer getSelectedAlignment() {
        if (this.characterData.getAlignmentId() == null) {
            return null;
        }
        return this.characterData.getAlignmentId().getId();
    }

    public void setSelectedAlignment(Integer selectedAlignment) {
        if (selectedAlignment == null) {
            this.characterData.setAlignmentId(null);
            return;
        }
        AlignmentMaster alignment = alignmentMasterFacade.find(selectedAlignment);
        this.characterData.setAlignmentId(alignment);
    }
    //  選択された種族  .. ここにある必要はないか？ EditCharacterRecord の Bean でよいかも
    Integer selectedRace;

    public Integer getSelectedRace() {
        if (this.characterData.getRaceId() == null) {
            return null;
        }
        return this.characterData.getRaceId().getId();
    }

    public void setSelectedRace(Integer selectedRace) {
        if (selectedRace == null) {
            this.characterData.setRaceId(null);
            return;
        }
        RaceMaster race = raceMasterFacade.find(selectedRace);
        this.characterData.setRaceId(race);
    }
    //選択された性別
    Integer selectedGender;

    public Integer getSelectedGender() {
        if (this.characterData.getGenderId() == null) {
            return null;
        }
        return this.characterData.getGenderId().getId();
    }

    public void setSelectedGender(Integer selectedGender) {
        if (selectedGender == null) {
            this.characterData.setGenderId(null);
            return;
        }
        GenderMaster gender = genderMasterFacade.find(selectedGender);
        this.characterData.setGenderId(gender);
    }
    // 選択された神格
    Integer selectedReligion;

    public Integer getSelectedReligion() {
        if (this.characterData.getReligionId() == null) {
            return null;
        }
        return this.characterData.getReligionId().getId();
    }

    public void setSelectedReligion(Integer selectedReligion) {
        if (selectedReligion == null) {
            this.characterData.setReligionId(null);
            return;
        }
        ReligionMaster religion = religionMasterFacade.find(selectedReligion);
        this.characterData.setReligionId(religion);
    }
    // キャラクタ技能レコード のリスト
    private List<CharacterSkillRecord> characterSkillRecordList;

    public List<CharacterSkillRecord> getCharacterSkillRecordList() {
        return characterSkillRecordList;
    }

    public void setCharacterSkillRecordList(List<CharacterSkillRecord> characterSkillRecordList) {
        this.characterSkillRecordList = characterSkillRecordList;
    }
    // キャラクタ編集画面で選択されたレベル値
    private Integer editCharacterPageSelectedLevel;

    public Integer getEditCharacterPageSelectedLevel() {
        return editCharacterPageSelectedLevel;
    }

    public void setEditCharacterPageSelectedLevel(Integer editCharacterSelectedLevel) {
        this.editCharacterPageSelectedLevel = editCharacterSelectedLevel;
    }
    // ------------- レベル別技能編集ページ用 レベル一覧 --------------------
    protected SelectItem[] levelArray;

    public SelectItem[] getLevelArray() {
        return levelArray;
    }

    public void setLevelArray(SelectItem[] levelArray) {
        this.levelArray = levelArray;
    }
    // キャラクタ成長レコード
    protected CharacterGrowthRecord characterGrowthRecord;

    public CharacterGrowthRecord getCharacterGrowthRecord() {
        return characterGrowthRecord;
    }

    public void setCharacterGrowthRecord(CharacterGrowthRecord characterGrowthRecord) {
        this.characterGrowthRecord = characterGrowthRecord;
    }
    //キャラクタ能力値レコードのリスト
    protected List<CharacterAbilityRecord> characterAbilityRecordList;

    public List<CharacterAbilityRecord> getCharacterAbilityRecordList() {
        return characterAbilityRecordList;
    }

    public void setCharacterAbilityRecordList(List<CharacterAbilityRecord> characterAbilityRecordList) {
        this.characterAbilityRecordList = characterAbilityRecordList;
    }
    // キャラクターセーブレコードのリスト
    protected List<CharacterSaveRecord> characterSaveRecordList;

    public List<CharacterSaveRecord> getCharacterSaveRecordList() {
        return characterSaveRecordList;
    }

    public void setCharacterSaveRecordList(List<CharacterSaveRecord> characterSaveRecordList) {
        this.characterSaveRecordList = characterSaveRecordList;
    }
    //クラス設定ページ用 編集しているクラス
    private ClassMaster classMaster;

    public ClassMaster getClassMaster() {
        return classMaster;
    }

    public void setClassMaster(ClassMaster classMaster) {
        this.classMaster = classMaster;
    }
    // ヒットポイント
    protected Integer hitPointTotal;

    public Integer getHitPointTotal() {
        return hitPointTotal;
    }

    public void setHitPointTotal(Integer totalHitPoint) {
        this.hitPointTotal = totalHitPoint;
    }
    // 装備データ
    protected CharacterEquipment characterEquipment;

    public CharacterEquipment getCharacterEquipment() {
        return characterEquipment;
    }

    public void setCharacterEquipment(CharacterEquipment characterEquipment) {
        this.characterEquipment = characterEquipment;
    }
    // 種族設定ページ用。
    protected RaceMaster raceMaster;

    /**
     * Get the value of raceMaster
     *
     * @return the value of raceMaster
     */
    public RaceMaster getRaceMaster() {
        return raceMaster;
    }

    /**
     * Set the value of raceMaster
     *
     * @param raceMaster new value of raceMaster
     */
    public void setRaceMaster(RaceMaster raceMaster) {
        this.raceMaster = raceMaster;
    }
    protected boolean mainArm;

    public boolean isMainArm() {
        return mainArm;
    }

    public void setMainArm(boolean mainArm) {
        this.mainArm = mainArm;
    }

    /*
     * この valueChangeListener は CharacterListPage の PostConstract の init
     * の「後」に呼ばれるようだ。なので init では 値の変更に気がついていない。。。
     */
    public void campaign_processValueChange(ValueChangeEvent vce) {
        Integer campaign = (Integer) vce.getNewValue();
        setCharacterListSelectedCampaign(campaign);
    }

    public Set getSelectedCharas() {
        return selectedCharas;
    }

    public void setSelectedCharas(Set selectedCharas) {
        this.selectedCharas = selectedCharas;
    }
    private HtmlDataTable characterListDataTable = new HtmlDataTable();

    public HtmlDataTable getCharacterListDataTable() {
        return characterListDataTable;
    }

    public void setCharacterListDataTable(HtmlDataTable hdt) {
        this.characterListDataTable = hdt;
    }

    @PostConstruct
    public void init() {
    }

    public String charaEditLink_action() {
        //管理Beanへ反映
        setCharacterData((CharacterData)characterListDataTable.getRowData());

        return "EditCharacterRecordPage";
    }

    public String charaViewButton_action() {
        //管理Beanへ反映
        setCharacterData((CharacterData)characterListDataTable.getRowData());

        return "PrintableCharacterRecordPage";
    }

    public String button1_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        return "EditClassPage";
    }

    public String saveButton_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }

    public String classListButton_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }

    public String newCharaButton_action() {
        CampaignMaster campaign;

        ////////////////////////////////
        //  CharacterRecord の作成
        ////////////////////////////////
        CharacterRecord characterRecord = new CharacterRecord();
        //経験値を0にセット
        characterRecord.setExperience(0);
        characterRecord.setAcArmor(0);
        characterRecord.setAcMiscMod(0);
        characterRecord.setAcShield(0);
        // もし選択されていれば現在選択しているキャンペーンをデフォルト値としてセット
        if (getCharacterListSelectedCampaign() != null) {
            campaign = campaignMasterFacade.find(getCharacterListSelectedCampaign());
            characterRecord.setCampaignId(campaign);
        }

        try {
            characterRecordFacade.create(characterRecord);
        } catch (Exception ex) {
            ex.printStackTrace();
            JsfUtil.addSuccessMessage("キャラクターの作成に失敗しました");
            return null;
        }

        ////////////////////////////////
        //  CharacterSkillRecord と CharacterSkillGrowthRecord の作成
        ////////////////////////////////
        //キャラクター技能毎のレコードを作成
        List<SkillMaster> skilllist = skillMasterFacade.findAll();
        List<CharacterSkillRecord> charaSkillList = new ArrayList<CharacterSkillRecord>();
        List<CharacterSkillGrowthRecord> charaSkillGrowthList = new ArrayList<CharacterSkillGrowthRecord>();

        for (SkillMaster skill : skilllist) {
            //あらたに CharacterSkillRecord を確保
            CharacterSkillRecord charaSkillRecord = new CharacterSkillRecord(characterRecord.getId().intValue(), skill.getId().intValue());
            charaSkillRecord.setMiscModifier(0);
            try {
                characterSkillRecordFacade.create(charaSkillRecord);
            } catch (Exception ex) {
                ex.printStackTrace();
                JsfUtil.addSuccessMessage("キャラクター技能レコードの作成に失敗しました");
                return null;
            }
            charaSkillList.add(charaSkillRecord);

            //あらたに 1Lv用の CharacterSkillGrowthRecord を作成
            CharacterSkillGrowthRecord skillGrowthRecord =
                    new CharacterSkillGrowthRecord(characterRecord.getId().intValue(), 1, skill.getId().intValue());
            //スキルポイントを0に初期化
            skillGrowthRecord.setSkillPoint(0);
            try {
                characterSkillGrowthRecordFacade.create(skillGrowthRecord);
            } catch (Exception e) {
                e.printStackTrace();
                JsfUtil.addSuccessMessage("キャラクターの技能成長レコードの作製に失敗しました");
                return null;
            }
            charaSkillGrowthList.add(skillGrowthRecord);
        }
        ////////////////////////////////////
        // CharacterAbilityRecordの作成
        //////////////////////////////////////
        List<AbilityMaster> abilityList = abilityMasterFacade.findAll();
        List<CharacterAbilityRecord> charaAbilityList = new ArrayList<CharacterAbilityRecord>();
        for (AbilityMaster ability : abilityList) {
            CharacterAbilityRecord abilityRecord = new CharacterAbilityRecord(characterRecord.getId().intValue(), ability.getId().intValue());
            //各能力値のデフォルトを10にセット
            abilityRecord.setBase(10);
            abilityRecord.setFeatModifier(0);
            abilityRecord.setMiscModifier(0);
            //キャラクター能力レコードを作成
            try {
                characterAbilityRecordFacade.create(abilityRecord);
            } catch (Exception ex) {
                ex.printStackTrace();
                JsfUtil.addSuccessMessage("キャラクター能力値(" + ability.getAbilityName() + ")レコードの作成に失敗しました");
                return null;
            }
            charaAbilityList.add(abilityRecord);
        }

        ////////////////////////////////////
        // CharacterSaveRecordの作成
        //////////////////////////////////////
        List<SaveMaster> saveList = saveMasterFacade.findAll();
        List<CharacterSaveRecord> charaSaveList = new ArrayList<CharacterSaveRecord>();
        for (SaveMaster save : saveList) {
            CharacterSaveRecord saveRecord = new CharacterSaveRecord(characterRecord.getId().intValue(), save.getId().intValue());
            //各能力値のデフォルトを10にセット
            saveRecord.setMiscModifier(0);
            //キャラクターセーヴレコードを作成
            try {
                characterSaveRecordFacade.create(saveRecord);
            } catch (Exception ex) {
                ex.printStackTrace();
                JsfUtil.addSuccessMessage("キャラクターセーヴレコードの作成に失敗しました");
                return null;
            }
            charaSaveList.add(saveRecord);
        }
        ////////////////////////////////////////////
        // CharacterEquipment の作成
        /////////////////////////////////////////////
        CharacterEquipment equip = new CharacterEquipment(characterRecord.getId().intValue());

        try {
            characterEquipmentFacade.create(equip);
        } catch (Exception ex) {
            ex.printStackTrace();
            JsfUtil.addSuccessMessage("キャラクター装備データの作成に失敗しました");
            return null;
        }

        /////////////////////////////////////////////////
        // CharacterGrowthRecord
        ////////////////////////////////////////////////
        List<CharacterGrowthRecord> charaGrowthList = new ArrayList<CharacterGrowthRecord>();
        // 1 レベルの成長レコードを作製
        CharacterGrowthRecord charaGrowth = new CharacterGrowthRecord(characterRecord.getId().intValue(), 1);
        try {
            characterGrowthRecordFacade.create(charaGrowth);
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addSuccessMessage("キャラクターの成長レコードの作成に失敗しました");
                   
            return null;
        }
        charaGrowthList.add(charaGrowth);

        // 能力値リスト、セーヴリスト、スキルリスト（その他のみ）、装備、成長リスト、スキル成長リスト
        //をキャラクターレコードにセット。
        characterRecord.setCharacterAbilityRecordList(charaAbilityList);
        characterRecord.setCharacterSaveRecordList(charaSaveList);
        characterRecord.setCharacterSkillRecordList(charaSkillList);
        characterRecord.setCharacterEquipment(equip);
        characterRecord.setCharacterGrowthRecordList(charaGrowthList);
        characterRecord.setCharacterSkillGrowthRecordList(charaSkillGrowthList);

        //反映させる。（実際に character_record テーブルの行が変更されるわけじゃないとおもうのだが・・
        characterRecordFacade.edit(characterRecord);

        //セッションBeanにキャラクターレコードをセット
        setCharacterData(new CharacterData(characterRecord));
        return "EditCharacterRecordPage";
    }

    /*
     * Check Box をつけようとしていることこ。 DataProvider の扱いがうまくいってない。というか、使い方がいまいち。
     *
     */
    public String listSummaryButton_action() {
        return "PrintableCharacterSummaryListPage";

    }
    private boolean charaSelected;

    /**
     * @return the charaSelected
     */
    public boolean isCharaSelected() {

        CharacterData charaData= (CharacterData)characterListDataTable.getRowData();

        return getSelectedCharas() != null && getSelectedCharas().contains(charaData);
    }

    /**
     * @param charaSelected the charaSelected to set
     */
    public void setCharaSelected(boolean charaSelected) {

        CharacterData charaData = (CharacterData) characterListDataTable.getRowData();
        if (charaData != null) {
            if (charaSelected) {
                getSelectedCharas().add(charaData);
            } else {
                getSelectedCharas().remove(charaData);
            }
        }
    }

    public String selectAllButton_action() {
        // TODO: Process the button click action. Return value is a navigation
        // case name where null will return to the same page.
        Set<CharacterData> charaSet = getSelectedCharas();
        //Clear first, just in case.
        charaSet.clear();
        for (CharacterData charaData : getCharacterDataList()) {
            charaSet.add(charaData);
        }
        return null;
    }

    public String releaseAllButton_action() {
        Set charaSet = getSelectedCharas();
        charaSet.clear();
        return null;
    }

    public Integer getCharacterLevel() {

        CharacterData charaData = (CharacterData)characterListDataTable.getRowData();
        return charaData.getLevel();
    }

    public String getClassList() {
        CharacterData charaData = (CharacterData)characterListDataTable.getRowData();
        return charaData.getClassList();
    }

    public String getLastChange() {
        CharacterData charaData = (CharacterData)characterListDataTable.getRowData();
        return charaData.getLastChange();
    }
}
