/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.CharacterGrowthRecord;
import com.cafeform.dndtools.entity.CampaignMaster;
import com.cafeform.dndtools.entity.CharacterEquipment;
import com.cafeform.dndtools.entity.SkillMaster;
import com.cafeform.dndtools.entity.CharacterRecord;
import com.cafeform.dndtools.entity.AbilityMaster;
import com.cafeform.dndtools.entity.CharacterSaveRecord;
import com.cafeform.dndtools.entity.ClassMaster;
import com.cafeform.dndtools.entity.GenderMaster;
import com.cafeform.dndtools.entity.CharacterSkillRecord;
import com.cafeform.dndtools.entity.CharacterSkillGrowthRecord;
import com.cafeform.dndtools.entity.ReligionMaster;
import com.cafeform.dndtools.entity.AlignmentMaster;
import com.cafeform.dndtools.entity.PlayerMaster;
import com.cafeform.dndtools.entity.SaveMaster;
import com.cafeform.dndtools.entity.CharacterAbilityRecord;
import com.cafeform.dndtools.entity.RaceMaster;
import com.cafeform.dndtools.ejb.GenderMasterFacade;
import com.cafeform.dndtools.ejb.CharacterGrowthRecordFacade;
import com.cafeform.dndtools.ejb.AlignmentMasterFacade;
import com.cafeform.dndtools.ejb.ReligionMasterFacade;
import com.cafeform.dndtools.ejb.CharacterSkillRecordFacade;
import com.cafeform.dndtools.ejb.ClassMasterFacade;
import com.cafeform.dndtools.ejb.CharacterSkillGrowthRecordFacade;
import com.cafeform.dndtools.ejb.CharacterEquipmentFacade;
import com.cafeform.dndtools.ejb.CharacterRecordFacade;
import com.cafeform.dndtools.ejb.CharacterAbilityRecordFacade;
import com.cafeform.dndtools.ejb.AbilityMasterFacade;
import com.cafeform.dndtools.ejb.ArmMasterFacade;
import com.cafeform.dndtools.ejb.ArmType1MasterFacade;
import com.cafeform.dndtools.ejb.RaceMasterFacade;
import com.cafeform.dndtools.ejb.CharacterSaveRecordFacade;
import com.cafeform.dndtools.ejb.CampaignMasterFacade;
import com.cafeform.dndtools.entity.CharacterArmRecord;
import java.io.Serializable;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import com.cafeform.dndtools.mbean.util.JsfUtil;
import com.cafeform.dndtools.mbean.util.PaginationHelper;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class CharacterSheetController implements Serializable {

    @Inject private CharacterRecordFacade characterRecordFacade;
    @Inject private ReligionMasterFacade religionMasterFacade;
    @Inject private GenderMasterFacade genderMasterFacade;
    @Inject private AlignmentMasterFacade alignmentMasterFacade;
    @Inject private RaceMasterFacade raceMasterFacade;
    @Inject private CampaignMasterFacade campaignMasterFacade;
    @Inject private CharacterSkillGrowthRecordFacade characterSkillGrowthRecordFacade;
    @Inject private CharacterGrowthRecordFacade characterGrowthRecordFacade;
    @Inject private CharacterEquipmentFacade characterEquipmentFacade;
    @Inject private CharacterSaveRecordFacade characterSaveRecordFacade;
    @Inject private CharacterAbilityRecordFacade characterAbilityRecordFacade;
    @Inject private CharacterSkillRecordFacade characterSkillRecordFacade;
    @Inject protected ClassMasterFacade classMasterFacade;
    @Inject private SessionController sessionController;
    @Inject private ApplicationController applicationController;

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }
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

    /*
     * 選択されたキャンペーン
     *
     * public Integer getSelectedCampaignId() { return
     * getSessionController().getSelectedCampaignId(); }
     *
     * public void setSelectedCampaignId(Integer selectedCampaign) {
     * if(selectedCampaign != getSessionController().getSelectedCampaignId()){
     * releaseAllButton_action(); recreatePagination(); recreateModel(); }
     * getSessionController().setSelectedScenarioRecord(null);
     * getSessionController().setSelectedCampaignId(selectedCampaign); }
     */
    /*
     * 選択されたキャンペーン
     */
    public CampaignMaster getSelectedCampaign() {
        return getSessionController().getSelectedCampaign();
    }

    public void setSelectedCampaign(CampaignMaster selectedCampaign) {
        if (getSessionController().getSelectedCampaign() != null
                && getSessionController().getSelectedCampaign().getId() != selectedCampaign.getId()) {
            releaseAllButton_action();
            recreatePagination();
            recreateModel();
        }
        getSessionController().setSelectedScenarioRecord(null);
        getSessionController().setSelectedCampaign(selectedCampaign);
    }

    /*
     * キャラクターデータのリスト
     */
    public List<CharacterData> getCharacterDataList() {        
        items = getPagination().createPageDataModel();

        List<CharacterData> charaDataList = new ArrayList<CharacterData>();
        for (Object obj : getItems()) {
            charaDataList.add(new CharacterData((CharacterRecord) obj));
        }
        return charaDataList;
    }

    public CharacterData getCharacterData() {
        return sessionController.getCharacterData();
    }

    public void setCharacterData(CharacterData characterData) {
        sessionController.setCharacterData(characterData);
    }
    // キャラクタの削除ボタンを有効比するかどうかの判定。使ってないか？

    public boolean isDeleteButtonDisabled() {
        if (getCharacterData().getId() == null) {
            return true;
        } else {
            return false;
        }
    }
    /*
     * キャンペーンの選択が必要？ 使ってる？
     */
    private boolean campaignSelectRequired;

    public boolean isCampaignSelectRequired() {
        return campaignSelectRequired;
    }

    public void setCampaignSelectRequired(boolean campaignSelectRequired) {
        this.campaignSelectRequired = campaignSelectRequired;
    }
    //キャンペーンの選択  
    // このプロパティはページ間でやり取りされるので、セッションBeanでよい
    Integer selectedCampaign;

    public Integer getCampaign() {
        if (getCharacterData().getCampaignId() == null) {
            return null;
        }
        return getCharacterData().getCampaignId().getId();
    }

    public void setCampaign(Integer campaignId) {
        if (campaignId == null) {
            getCharacterData().setCampaignId(null);
            return;
        }
        CampaignMaster campaign = campaignMasterFacade.find(campaignId);
        getCharacterData().setCampaignId(campaign);
    }
    /*
     * 選択された属性
     */
    Integer selectedAlignment;

    public Integer getSelectedAlignment() {
        if (getCharacterData().getAlignmentId() == null) {
            return null;
        }
        return getCharacterData().getAlignmentId().getId();
    }

    public void setSelectedAlignment(Integer selectedAlignment) {
        if (selectedAlignment == null) {
            getCharacterData().setAlignmentId(null);
            return;
        }
        AlignmentMaster alignment = alignmentMasterFacade.find(selectedAlignment);
        getCharacterData().setAlignmentId(alignment);
    }
    /*
     * 選択された種族
     */
    Integer selectedRace;

    public Integer getSelectedRace() {
        if (getCharacterData().getRaceId() == null) {
            return null;
        }
        return getCharacterData().getRaceId().getId();
    }

    public void setSelectedRace(Integer selectedRace) {
        if (selectedRace == null) {
            getCharacterData().setRaceId(null);
            return;
        }
        RaceMaster race = raceMasterFacade.find(selectedRace);
        getCharacterData().setRaceId(race);
    }

    /*
     * 選択された性別
     */
    Integer selectedGender;

    public Integer getSelectedGender() {
        if (getCharacterData().getGenderId() == null) {
            return null;
        }
        return getCharacterData().getGenderId().getId();
    }

    public void setSelectedGender(Integer selectedGender) {
        if (selectedGender == null) {
            getCharacterData().setGenderId(null);
            return;
        }
        GenderMaster gender = genderMasterFacade.find(selectedGender);
        getCharacterData().setGenderId(gender);
    }
    /*
     * 選択された神格
     */
    Integer selectedReligion;

    public Integer getSelectedReligion() {
        if (getCharacterData().getReligionId() == null) {
            return null;
        }
        return getCharacterData().getReligionId().getId();
    }

    public void setSelectedReligion(Integer selectedReligion) {
        if (selectedReligion == null) {
            getCharacterData().setReligionId(null);
            return;
        }
        ReligionMaster religion = religionMasterFacade.find(selectedReligion);
        getCharacterData().setReligionId(religion);
    }
    // キャラクタ編集画面で選択されたレベル値
    private Integer editCharacterPageSelectedLevel;

    public Integer getEditCharacterPageSelectedLevel() {
        return editCharacterPageSelectedLevel;
    }

    public void setEditCharacterPageSelectedLevel(Integer editCharacterSelectedLevel) {
        this.editCharacterPageSelectedLevel = editCharacterSelectedLevel;
    }

    /*
     * レベル別技能編集ページ用 レベル一覧
     */
    protected SelectItem[] levelArray;

    public SelectItem[] getLevelArray() {
        return levelArray;
    }

    public void setLevelArray(SelectItem[] levelArray) {
        this.levelArray = levelArray;
    }

    /*
     * この valueChangeListener は CharacterListPage の PostConstract の init
     * の「後」に呼ばれるようだ。なので init では 値の変更に気がついていない。。。 
     * 呼ばれるタイミングが不明? だれよりも最初に呼ばれてほしいものだが?
     */
    public void campaign_processValueChange(ValueChangeEvent vce) {
        items = null;        
        CampaignMaster campaign = (CampaignMaster) vce.getNewValue();
        releaseAllButton_action();
        recreatePagination();
        recreateModel();
        setSelectedCampaign(campaign);
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
        setCharacterData((CharacterData) characterListDataTable.getRowData());

        return "EditCharacterRecordPage";
    }

    public String charaViewButton_action() {
        //管理Beanへ反映
        setCharacterData((CharacterData) characterListDataTable.getRowData());

        return "PrintableCharacterRecordPage";
    }

    public String button1_action() {
        return "EditClassPage";
    }

    public String classListButton_action() {
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
        if (getSelectedCampaign() != null) {
            campaign = getSelectedCampaign();
            characterRecord.setCampaignId(campaign);
        }

        try {
            characterRecordFacade.create(characterRecord);
        } catch (Exception ex) {
            JsfUtil.addSuccessMessage("キャラクターの作成に失敗しました");
            return null;
        }

        ////////////////////////////////
        //  CharacterSkillRecord と CharacterSkillGrowthRecord の作成
        ////////////////////////////////
        //キャラクター技能毎のレコードを作成
        List<SkillMaster> skilllist = applicationController.getSkillList();
        List<CharacterSkillRecord> charaSkillList = new ArrayList<CharacterSkillRecord>();
        List<CharacterSkillGrowthRecord> charaSkillGrowthList = new ArrayList<CharacterSkillGrowthRecord>();

        for (SkillMaster skill : skilllist) {
            //あらたに CharacterSkillRecord を確保
            CharacterSkillRecord charaSkillRecord = new CharacterSkillRecord(characterRecord.getId().intValue(), skill.getId().intValue());
            charaSkillRecord.setMiscModifier(0);
            try {
                characterSkillRecordFacade.create(charaSkillRecord);
            } catch (Exception ex) {
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
                JsfUtil.addSuccessMessage("キャラクターの技能成長レコードの作製に失敗しました");
                return null;
            }
            charaSkillGrowthList.add(skillGrowthRecord);
        }
        ////////////////////////////////////
        // CharacterAbilityRecordの作成
        //////////////////////////////////////
        List<AbilityMaster> abilityList = applicationController.getAbilityList();
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
        List<SaveMaster> saveList = applicationController.getSaveList();
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

    /*
     * チェックボックスで選択されたキャラクターのセット
     */
    public Map getCheckedCharacterMap() {
        return getSessionController().getCheckedCharacterMap();
    }

    public void setCheckedCharacterMap(Map checkedCharas) {
        getSessionController().setCheckedCharacterMap(checkedCharas);
    }

    /**
     * チェックボックが選択されているか?
     */
    public boolean isCharacterChecked() {

        CharacterData charaData = (CharacterData) characterListDataTable.getRowData();

        return getCheckedCharacterMap().containsKey(charaData.getCharacterRecord().getId());
    }

    public void setCharacterChecked(boolean charaChecked) {

        CharacterData charaData = (CharacterData) characterListDataTable.getRowData();
        if (charaData != null) {
            if (charaChecked && !getCheckedCharacterMap().containsKey(charaData.getCharacterRecord().getId())) {
                getCheckedCharacterMap().put(charaData.getCharacterRecord().getId().intValue(), charaData);
            } else if (!charaChecked && getCheckedCharacterMap().containsKey(charaData.getCharacterRecord().getId())) {
                getCheckedCharacterMap().remove(charaData.getCharacterRecord().getId());
            }
        }
    }

    public String selectAllButton_action() {
        Map<Integer, CharacterData> charaMap = getCheckedCharacterMap();
        //Clear first, just in case.
        charaMap.clear();
        for (CharacterData charaData : getCharacterDataList()) {
            charaMap.put(charaData.getCharacterRecord().getId().intValue(), charaData);
        }
        return null;
    }

    public String releaseAllButton_action() {
        Map charaMap = getCheckedCharacterMap();
        charaMap.clear();
        return null;
    }
    protected HtmlDataTable abilityTable = new HtmlDataTable();

    public HtmlDataTable getAbilityTable() {
        return abilityTable;
    }

    public void setAbilityTable(HtmlDataTable hdt) {
        this.abilityTable = hdt;
    }
    protected HtmlDataTable skillTable = new HtmlDataTable();

    public HtmlDataTable getSkillTable() {
        return skillTable;
    }

    public void setSkillTable(HtmlDataTable hdt) {
        this.skillTable = hdt;
    }
    protected HtmlSelectOneMenu classDropDown = new HtmlSelectOneMenu();

    public HtmlSelectOneMenu getClassDropDown() {
        return classDropDown;
    }

    public void setClassDropDown(HtmlSelectOneMenu hsom) {
        this.classDropDown = hsom;
    }
    protected HtmlDataTable growthTable = new HtmlDataTable();

    public HtmlDataTable getGrowthTable() {
        return growthTable;
    }

    public void setGrowthTable(HtmlDataTable hdt) {
        this.growthTable = hdt;
    }
    private HtmlDataTable saveTable = new HtmlDataTable();

    public HtmlDataTable getSaveTable() {
        return saveTable;
    }

    public void setSaveTable(HtmlDataTable hdt) {
        this.saveTable = hdt;
    }

    public void textField1_processValueChange(ValueChangeEvent vce) {
    }

    public String charaListButton_action() {
        return "CharacterListPage";
    }

    public String saveButton_action() {

        CharacterData charaData = getCharacterData();
        CharacterEquipment equip = getCharacterData().getCharacterEquipment();
        List<CharacterSkillRecord> skillRecordList = getCharacterData().getCharacterSkillRecordList();
        List<CharacterSkillGrowthRecord> skillGrowthList = getCharacterData().getCharacterSkillGrowthRecordList();
        List<CharacterGrowthRecord> growthList = getCharacterData().getCharacterGrowthRecordList();
        List<CharacterAbilityRecord> abilityList = getCharacterData().getCharacterAbilityRecordList();
        List<CharacterSaveRecord> saveList = getCharacterData().getCharacterSaveRecordList();

        /*
         * セーブ時には 順番を変えるためにキャラクターリストの一覧を再生性する
         */
        releaseAllButton_action();
        recreatePagination();
        recreateModel();

        //更新時間を記録
        Date date = new Date();
        charaData.setSaveTime(date);

        try {
            charaData.updateGrowthRecord();            
            // CharacterRecord(CHARACTER_RECORD)の更新            
            characterRecordFacade.edit(charaData.getCharacterRecord());
            characterEquipmentFacade.edit(equip);
            for (CharacterGrowthRecord growth : growthList) {
                characterGrowthRecordFacade.edit(growth);
            }
            for (CharacterSkillRecord skillRecord : skillRecordList) {
                characterSkillRecordFacade.edit(skillRecord);
            }
            for (CharacterSkillGrowthRecord skillGrowth : skillGrowthList) {
                characterSkillGrowthRecordFacade.edit(skillGrowth);
            }
            for (CharacterAbilityRecord ability : abilityList) {
                characterAbilityRecordFacade.edit(ability);
            }
            for (CharacterSaveRecord save : saveList) {
                characterSaveRecordFacade.edit(save);
            }
            JsfUtil.addSuccessMessage("保存されました");
        } catch (Exception ex) {
            JsfUtil.addSuccessMessage("キャラクターの保存に失敗しました");
            return null;
        }

        return null;
    }

    public String editCharaDeleteButton_action() {

        try {
            characterRecordFacade.remove(getCharacterData().getCharacterRecord());
        } catch (Exception ex) {
            JsfUtil.addSuccessMessage("削除に失敗しました");
            return null;
        }
        releaseAllButton_action();
        recreatePagination();
        recreateModel();
        return "CharacterListPage";
    }

    /*
     * ベース能力値
     */
    public Integer getAbilityBase() {
        int abid = abilityTable.getRowIndex() + 1;
        return getCharacterData().getAbilityBaseById(abid);
    }

    public void setAbilityBase(Integer newVal) {
        int ability = abilityTable.getRowIndex() + 1;
        getCharacterData().setAbilityBaseById(ability, newVal);
    }

    /**
     * 能力値 その他修正
     */
    public Integer getAbilityMiscModifier() {
        int abid = abilityTable.getRowIndex() + 1;
        return getCharacterData().getAbilityMiscModifierById(abid);
    }

    public void setAbilityMiscModifier(Integer newVal) {
        int ability = abilityTable.getRowIndex() + 1;
        getCharacterData().setAbilityMiscModifierById(ability, newVal);
    }

    public void setAbilityMiscModifierById(int id, Integer newVal) {
        getCharacterData().getCharacterAbilityRecordList().get(id - 1).setMiscModifier(newVal);
    }

    /*
     * 能力値 特技修正値
     */
    public Integer getAbilityFeatModifier() {
        int abid = abilityTable.getRowIndex() + 1;
        return getCharacterData().getAbilityFeatModifierById(abid);
    }

    public void setAbilityFeatModifier(Integer newVal) {
        int ability = abilityTable.getRowIndex() + 1;
        getCharacterData().setAbilityFeatModifierById(ability, newVal);
    }

    /*
     * 能力値 種族 修正値
     */
    public Integer getAbilityRaceModifier() {
        int abid = abilityTable.getRowIndex() + 1;

        return getCharacterData().getAbilityRaceModifierById(abid);
    }

    /*
     * 能力値 レベル修正値 (計算値）
     */
    public Integer getAbilityLevelModifier() {
        int abid = abilityTable.getRowIndex() + 1;
        return getCharacterData().getAbilityLevelModifierById(abid);
    }

    /*
     * 能力値 合計
     */
    public Integer getAbilityTotal() {
        int abid = abilityTable.getRowIndex() + 1;
        return getCharacterData().getAbilityTotalById(abid);
    }

    /*
     * 能力値 修正値
     */
    public Integer getAbilityModifier() {
        int ability = abilityTable.getRowIndex() + 1;
        return getCharacterData().getAbilityModifierById(ability);
    }

    /*
     * 技能 対応能力修正値
     */
    public Integer getSkillAbilityModifier() {
        int skill = skillTable.getRowIndex() + 1;
        return getCharacterData().getSkillAbilityModifierById(skill);
    }

    /*
     * 技能 対応能力値名
     */
    public String getSkillAbilityName() {
        int skill = skillTable.getRowIndex() + 1;
        return getCharacterData().getSkillAbilityNameById(skill);
    }

    /*
     * 技能 対応能力値名 省略名
     */
    public String getSkillAbilityShortName() {
        int skill = skillTable.getRowIndex() + 1;
        return getCharacterData().getSkillAbilityShortNameById(skill);
    }

    /*
     * 技能 ポイント
     */
    public Integer getSkillTotalPoint() {
        int skill = skillTable.getRowIndex() + 1;
        return getCharacterData().getSkillTotalPointById(skill);
    }

    /*
     * 技能 判定値
     */
    public Integer getSkillTotalCheckModifier() {
        return getSkillAbilityModifier()
                + getSkillTotalRank()
                + getSkillMiscModifier()
                + getSkillArmorModifier()
                + getSkillSynergyModifier();
    }

    /*
     * 技能 その他修正
     */
    public Integer getSkillMiscModifier() {
        Integer result;
        int index = skillTable.getRowIndex();
        List<CharacterSkillRecord> skillRecordList = getCharacterData().getCharacterSkillRecordList();
        // skillTable の RowIndex と List の index は同一の特技をさしているはず
        result = skillRecordList.get(index).getMiscModifier();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    public void setSkillMiscModifier(Integer skillMiscModifier) {
        int index = skillTable.getRowIndex();
        List<CharacterSkillRecord> skillRecord = getCharacterData().getCharacterSkillRecordList();
        // skillTable の RowIndex と List の index は同一の特技をさしているはず
        skillRecord.get(index).setMiscModifier(skillMiscModifier);
    }
    /*
     * 技能 ランク（計算値) ポイントとクラスから計算する
     */
    protected Integer skillRank;
    protected Integer skillRankByLevelAndSkill;

    public Integer getSkillTotalRank() {
        int result;
        int skillId = skillTable.getRowIndex() + 1;

        result = getCharacterData().getSkillTotalRankById(skillId);
        return result;
    }

    /*
     * 技能がランク無しでも実施可能か
     */
    protected boolean skillAcceptNoRankBySkillId;
    protected String abilityCheckAcceptNoRank;

    public String getSkillCheckAcceptNoRank() {
        int skill = skillTable.getRowIndex() + 1;
        if (getCharacterData().isSkillAcceptoRankBySkillId(skill)) {
            return "■";
        } else {
            return "□";
        }
    }


    /*
     * 技能 鎧、盾ペナルティ
     */
    public Integer getSkillArmorModifier() {
        Integer result;
        int skillId = skillTable.getRowIndex() + 1;
        result = getCharacterData().getSkillArmorModifierById(skillId);
        return result;
    }

    /**
     * 技能 相乗効果
     */
    public Integer getSkillSynergyModifier() {
        Integer result;
        int skillId = skillTable.getRowIndex() + 1;
        result = getCharacterData().getSkillSynergyModifierById(skillId);
        return result;
    }

    /**
     * 技能 ランクもっているか？
     */
    public String getSkillRankCheck() {
        int skillId = skillTable.getRowIndex() + 1;
        SkillMaster skill = applicationController.getSkillBySkillId(skillId);
        ClassMaster klass;

        List<CharacterGrowthRecord> growthList = getCharacterData().getCharacterGrowthRecordList();

        if (getCharacterData().hasSkillRankBySkill(skill)) {
            return "■";
        }
        return "□";
    }

    /*
     * キャラクタレベル毎のクラスの選択
     */
    public Integer getSelectedClassByRow() {
        int index = growthTable.getRowIndex();
        CharacterGrowthRecord growth = getCharacterData().getCharacterGrowthRecordList().get(index);
        if (growth.getClassId() == null) {
            return null;
        } else {
            return growth.getClassId().getId();
        }

    }

    public void setSelectedClassByRow(Integer classId) {
        int index = growthTable.getRowIndex();
        CharacterGrowthRecord growth = getCharacterData().getCharacterGrowthRecordList().get(index);
        ClassMaster klass;

        if (classId == null) {
            growth.setClassId(null);
            return;

        }
        klass = classMasterFacade.find(classId);
        growth.setClassId(klass);
    }

    /*
     * キャラクタレベル 4 レベル毎の上昇能力値の設定
     */
    public Integer getSelectedAbilityEnhancementByRow() {
        int index = growthTable.getRowIndex();
        CharacterGrowthRecord growth = getCharacterData().getCharacterGrowthRecordList().get(index);
        if (growth.getAbilityEnhancement() == null) {
            return null;
        } else {
            return growth.getAbilityEnhancement();
        }
    }

    public void setSelectedAbilityEnhancementByRow(Integer abilityId) {
        int index = growthTable.getRowIndex();
        CharacterGrowthRecord growth = getCharacterData().getCharacterGrowthRecordList().get(index);

        if (abilityId == null) {
            growth.setAbilityEnhancement(null);
            return;
        }
        growth.setAbilityEnhancement(abilityId);
    }

    /*
     * キャラクタレベル毎のスキルの変更ボタン
     */
    public String editSkillButton_action() {
        int index = growthTable.getRowIndex();
        // Lv とキャラクターレコードを元に、キャラクター成長レコードを得、セッションBeanにセットする
        getSessionController().setCharacterGrowthRecord(getCharacterData().getCharacterGrowthRecordList().get(index));
        return "EditCharacterPerLevelPage";
    }

    /*
     * セーヴ 対応能力修正値
     */
    public Integer getSaveAbilityModifier() {
        int saveId = saveTable.getRowIndex() + 1;
        return getCharacterData().getSaveAbilityModifierById(saveId);
    }

    /*
     * セーヴボーナス クラス合計
     */
    protected Integer saveClassBonus;

    public Integer getSaveClassBonus() {
        int saveId = saveTable.getRowIndex() + 1;
        return getCharacterData().getSaveClassBonusById(saveId);
    }

    /*
     * セーヴ その他修正値
     */
    public Integer getSaveMiscModifier() {
        int saveId = saveTable.getRowIndex() + 1;
        return getCharacterData().getSaveMiscModifierById(saveId);
    }

    public void setSaveMiscModifier(Integer miscModifier) {
        int saveId = saveTable.getRowIndex() + 1;
        CharacterSaveRecord saveRecord = getCharacterData().getCharacterSaveRecordList().get(saveId - 1);
        saveRecord.setMiscModifier(miscModifier);
    }

    /*
     * セーヴ 種族修正値
     */
    public Integer getSaveRaceModifier() {
        int saveId = saveTable.getRowIndex() + 1;
        return getCharacterData().getSaveRaceModifierById(saveId);
    }

    /*
     * セーヴボーナス トータル計
     */
    public Integer getSaveTotal() {
        int saveId = saveTable.getRowIndex() + 1;
        return getCharacterData().getSaveTotalById(saveId);
    }

    /*
     * イニシアチブ 技能修正
     */
    public Integer getInitiativeFeatModifier() {
        Integer mod = getCharacterData().getInitiativeFeatModifier();
        if (mod == null) {
            return new Integer(0);
        }
        return mod;
    }

    public void setInitiativeFeatModifier(Integer modifier) {
        getCharacterData().setInitiativeFeatModifier(modifier);
    }

    public void setSpeedFeatModifier(Integer speedFeatModifier) {
        getCharacterData().setSpeedFeatModifier(speedFeatModifier);
    }

    public void textField13_processValueChange(ValueChangeEvent vce) {
    }

    /**
     * 技能編集ボタン（レベル無し)
     */
    public String editSkillNomalButton_action() {
        // キャラクターレコードを元に、キャラクター成長レコードを得、セッションBeanにセットする。Lv は 1 固定
        getSessionController().setCharacterGrowthRecord(getCharacterData().getCharacterGrowthRecordList().get(0));
        return "EditCharacterPerLevelPage";
    }

    /**
     * 能力値上昇の選択リストを非表示にするかどうかを決める
     */
    public boolean isAbilityEnhancementDisabled() {
        int level = growthTable.getRowIndex() + 1;
        // 4 レベルの倍数のときは表示
        return (level % 4 != 0);
    }

    public void dropdown2_processValueChange(ValueChangeEvent vce) {
    }

    public String editCharaCancelButton_action() {
        if (getCharacterData().getSaveTime() == null) {
            return editCharaDeleteButton_action();
        } else {
            return "CharacterListPage";
        }
    }

    public String viewLink_action() {
        return "PrintableCharacterRecordPage";
    }

    /**
     * アイテム 武器 TODO: 未実装
     */
    /*
     * public String getArm1 (){ ArmMaster arm1 =
     * getCharacterData().getCharacterEquipment().getArm1(); if(arm1 != null) {
     * return arm1.getName(); } return "未装備"; } public String getArm2 (){
     * ArmMaster arm2 = getCharacterData().getCharacterEquipment().getArm2();
     * if(arm2 != null) { return arm2.getName(); } return "未装備"; }
     */
    public Boolean isTriableSkill() {
        SkillMaster skill = (SkillMaster) skillTable.getRowData();
        return getCharacterData().isSkillAcceptoRankBySkillId(skill.getId())
                || getCharacterData().hasSkillRankBySkill(skill);
    }

    /**
     * 改行を<br>に変換し、半角スペースを &nbsp に変換
     */
    public static String textToHtml(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        StringCharacterIterator sci = new StringCharacterIterator(str);
        for (char c = sci.current(); c != StringCharacterIterator.DONE; c = sci.next()) {
            if (c == '\n') {
                sb.append("<br>");
            } else if (c == ' ') {
                sb.append("&nbsp;");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String getAbilityShortName(AbilityMaster ability) {
        String name = ability.getAbilityName();
        return (new StringBuilder()).append("\u3010").append(name.substring(0, 1)).append("\u3011").toString();
    }
    private PaginationHelper pagination;
    private DataModel items = null;

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    if (getSelectedCampaign() == null) {
                        return characterRecordFacade.count();
                    } else {
                        return characterRecordFacade.countByCampaign(getSelectedCampaign());
                    }
                }

                @Override
                public DataModel createPageDataModel() {
                    if (getSelectedCampaign() == null) {
                        return new ListDataModel(characterRecordFacade.findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    } else {
                        return new ListDataModel(characterRecordFacade.findRangeByCampaign(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, getSelectedCampaign()));
                    }
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "/characterSheet/CharacterListPage";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "/characterSheet/CharacterListPage";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(characterRecordFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(characterRecordFacade.findAll(), true);
    }

    public void recreateList() {
        releaseAllButton_action();
        recreatePagination();
        recreateModel();
    }
    
    public List<CharacterArmRecord> getCharacterArmRecordList ()
    {
        return getCharacterData().getCharacterRecord().getCharacterArmRecordList();
    }
    
    /*
     * Arms edit button.
     */
    public String editArmButton_action() {
        getSessionController().setCharacterArmRecord(getCharacterArmRecordList ());
        return "EditCharacterArmPage";
    }
}
