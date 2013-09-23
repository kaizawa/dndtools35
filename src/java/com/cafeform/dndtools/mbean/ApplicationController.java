/*
 * ApplicationController1.java
 *
 * Created on 2008/12/23, 23:35:51
 */
package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.SizeMaster;
import com.cafeform.dndtools.entity.CampaignMaster;
import com.cafeform.dndtools.entity.ReligionMaster;
import com.cafeform.dndtools.entity.AlignmentMaster;
import com.cafeform.dndtools.entity.BonusRankMaster;
import com.cafeform.dndtools.entity.DiceMaster;
import com.cafeform.dndtools.entity.SkillMaster;
import com.cafeform.dndtools.entity.ClassMaster;
import com.cafeform.dndtools.entity.AbilityMaster;
import com.cafeform.dndtools.entity.SaveMaster;
import com.cafeform.dndtools.entity.GenderMaster;
import com.cafeform.dndtools.entity.RaceMaster;
import com.cafeform.dndtools.ejb.ClassMasterFacade;
import com.cafeform.dndtools.ejb.SkillMasterFacade;
import com.cafeform.dndtools.ejb.SizeMasterFacade;
import com.cafeform.dndtools.ejb.GenderMasterFacade;
import com.cafeform.dndtools.ejb.DiceMasterFacade;
import com.cafeform.dndtools.ejb.AlignmentMasterFacade;
import com.cafeform.dndtools.ejb.BonusRankMasterFacade;
import com.cafeform.dndtools.ejb.AbilityMasterFacade;
import com.cafeform.dndtools.ejb.ArmMasterFacade;
import com.cafeform.dndtools.ejb.ArmType1MasterFacade;
import com.cafeform.dndtools.ejb.ArmType2MasterFacade;
import com.cafeform.dndtools.ejb.ArmType3MasterFacade;
import com.cafeform.dndtools.ejb.RaceMasterFacade;
import com.cafeform.dndtools.ejb.CampaignMasterFacade;
import com.cafeform.dndtools.ejb.DamageTypeMasterFacade;
import com.cafeform.dndtools.ejb.MonsterMasterFacade;
import com.cafeform.dndtools.ejb.ReligionMasterFacade;
import com.cafeform.dndtools.ejb.SaveMasterFacade;
import com.cafeform.dndtools.ejb.TypeMasterFacade;
import com.cafeform.dndtools.entity.ArmMaster;
import com.cafeform.dndtools.entity.ArmType1Master;
import com.cafeform.dndtools.entity.ArmType2Master;
import com.cafeform.dndtools.entity.ArmType3Master;
import com.cafeform.dndtools.entity.DamageTypeMaster;
import com.cafeform.dndtools.entity.MonsterMaster;
import com.cafeform.dndtools.entity.TypeMaster;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 * <p>Application scope data bean for your application. Create properties here
 * to represent cached data that should be made available to all users and pages
 * in the application.</p>
 *
 * <p>An instance of this class will be created for you automatically, the first
 * time your application evaluates a value binding expression or method binding
 * expression that references a managed bean using this class.</p>
 *
 * @author ka78231
 */
@Named
@ApplicationScoped
public class ApplicationController {

    @EJB private SizeMasterFacade sizeMasterFacade;
    @EJB private CampaignMasterFacade campaignMasterFacade;
    @EJB private ClassMasterFacade classMasterFacade;
    @EJB private ReligionMasterFacade religionMasterFacade;
    @EJB private AlignmentMasterFacade alignmentMasterFacade;
    @EJB private GenderMasterFacade genderMasterFacade;
    @EJB private RaceMasterFacade raceMasterFacade;
    @EJB private DiceMasterFacade diceMasterFacade;
    @EJB private BonusRankMasterFacade bonusRankMasterFacade;
    @EJB private SaveMasterFacade saveMasterFacade;
    @EJB private SkillMasterFacade skillMasterFacade;
    @EJB private AbilityMasterFacade abilityMasterFacade;
    @EJB private DamageTypeMasterFacade damageTypeMasterFacade;
    @EJB private ArmMasterFacade armMasterFacade;
    @EJB private MonsterMasterFacade monsterMasterFacade;
    @EJB private TypeMasterFacade typeMasterFacade;
    @EJB private ArmType1MasterFacade armType1MasterFacade;
    @EJB private ArmType2MasterFacade armType2MasterFacade;
    @EJB private ArmType3MasterFacade armType3MasterFacade; 
    
    /**
     * <p>Construct a new application data bean instance.</p>
     */
    public ApplicationController() {
    }

    @PostConstruct
    public void init() {
        //アプリケーション起動時のリストをアプリケーションBeanに保存しておく
        //アビリティ、技能、性別、ダイスは追加されることはないので・・・
        setAbilityList(abilityMasterFacade.findAll());
        setSkillList(skillMasterFacade.findAll());
        setSaveList(saveMasterFacade.findAll());
        setBonusRankList(bonusRankMasterFacade.findAll());
        setDiceList(diceMasterFacade.findAll());

        ////////////////////////
        /// ボーナスランクの配列
        ////////////////////////
        List<BonusRankMaster> rankFindAll = getBonusRankList();
        List<SelectItem> tempRankList = new ArrayList<SelectItem>();
        //未選択状態
        tempRankList.add(new SelectItem(null, "未選択"));
        for (BonusRankMaster rank : rankFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(rank.getId());
            selectItem.setLabel(rank.getRankName());
            tempRankList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] newRankArray = tempRankList.toArray(new SelectItem[0]);
        //セッションBeanにセット        
        setRankArray(newRankArray);

        ///////////////////////
        /// 技能の配列
        ///////////////////////
        List<SkillMaster> skillFindAll = getSkillList();
        List<SelectItem> tempSkillList = new ArrayList<SelectItem>();
        tempSkillList.add(new SelectItem(null, "未選択"));
        for (SkillMaster skill : skillFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(skill.getId());
            selectItem.setLabel(skill.getSkillName());

            tempSkillList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempSkillArray = tempSkillList.toArray(new SelectItem[0]);
        //セッションBeanにセット
        setSkillArray(tempSkillArray);

        ///////////////////////
        /// ダイスの配列
        ///////////////////////
        List<DiceMaster> diceFindAll = getDiceList();
        List<SelectItem> tempDiceList = new ArrayList<SelectItem>();
        //未選択状態
        tempDiceList.add(new SelectItem(null, "未選択"));
        for (DiceMaster dice : diceFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(dice.getId());
            selectItem.setLabel(dice.getName());
            tempDiceList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempDieceArray = tempDiceList.toArray(new SelectItem[0]);
        //セッションBeanにセット
        setDiceArray(tempDieceArray);

        /////////////////////////
        //  種族の配列
        /////////////////////////
        List<RaceMaster> raceFindAll = raceMasterFacade.findAll();
        List<SelectItem> raseSelectedItemList = new ArrayList<SelectItem>();
        //未選択状態
        raseSelectedItemList.add(new SelectItem(null, "未選択"));

        for (RaceMaster race : raceFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(race.getId());
            selectItem.setLabel(race.getRaceName());

            raseSelectedItemList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempRaceArray = raseSelectedItemList.toArray(new SelectItem[0]);
        //セッションBeanへのセット
        setRaceArray(tempRaceArray);

        ////////////////////////
        // 性別の配列
        ///////////////////////
        List<GenderMaster> genderFindAll = genderMasterFacade.findAll();
        List<SelectItem> genderList = new ArrayList<SelectItem>();
        //未選択状態
        genderList.add(new SelectItem(null, "未選択"));
        for (GenderMaster gender : genderFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(gender.getId());
            selectItem.setLabel(gender.getGender());
            genderList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempGenderArray = genderList.toArray(new SelectItem[0]);
        //セッションBeanへのセット
        setGenderArray(tempGenderArray);

        //////////////////////////
        // 属性の配列
        /////////////////////////
        List<AlignmentMaster> alignmentFindAll = alignmentMasterFacade.findAll();
        List<SelectItem> alignmentSelectItemList = new ArrayList<SelectItem>();
        //未選択状態
        alignmentSelectItemList.add(new SelectItem(null, "未選択"));
        for (AlignmentMaster align : alignmentFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(align.getId());
            selectItem.setLabel(align.getAlignmentName());
            alignmentSelectItemList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempAlignmentArray = alignmentSelectItemList.toArray(new SelectItem[0]);
        //セッションBEANへのセット
        setAlignmentArray(tempAlignmentArray);

        /////////////////////////////////////////////////
        // 神格の配列
        /////////////////////////////////////////////////
        List<ReligionMaster> religionFindAll = religionMasterFacade.findAll();
        List<SelectItem> religionList = new ArrayList<SelectItem>();
        //未選択状態
        religionList.add(new SelectItem(null, "未選択"));
        for (ReligionMaster religion : religionFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(religion.getId());
            selectItem.setLabel(religion.getReligionName());

            religionList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempReligionArray = religionList.toArray(new SelectItem[0]);
        //セッションBeanへのセット
        setReligionArray(tempReligionArray);

        /////////////////////////////////////////
        // クラスの配列
        ////////////////////////////////////////
        List<ClassMaster> classFindAll = classMasterFacade.findAll();
        List<SelectItem> classList = new ArrayList<SelectItem>();
        //未選択状態
        classList.add(new SelectItem(null, "未選択"));
        for (ClassMaster klass : classFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(klass.getId());
            selectItem.setLabel(klass.getClassName());
            classList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempClassArray = classList.toArray(new SelectItem[0]);
        //セッションBEANへのセット
        setClassArray(tempClassArray);

        //////////////////////////////////////
        ///   キャンペーンの配列
        ///////////////////////////////////////
        List<CampaignMaster> cmpFindAll = campaignMasterFacade.findAll();
        List<SelectItem> campaignList = new ArrayList<SelectItem>();
        //未選択状態
        campaignList.add(new SelectItem(null, "未選択"));
        for (CampaignMaster campaign : cmpFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(campaign.getId());
            selectItem.setLabel(campaign.getCampaignName());
            campaignList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempCampaignArray = campaignList.toArray(new SelectItem[0]);
        //キャンペーンのセット
        setCampaignArray(tempCampaignArray);

        /////////////////////////////////////////
        // 能力値の配列
        ////////////////////////////////////////
        List<AbilityMaster> abilityFindAll = abilityMasterFacade.findAll();
        List<SelectItem> tempAbilityList = new ArrayList<SelectItem>();
        //未選択状態
        tempAbilityList.add(new SelectItem(null, "未選択"));
        for (AbilityMaster ability : abilityFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(ability.getId());
            selectItem.setLabel(ability.getAbilityName());
            tempAbilityList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempAbilityArray = tempAbilityList.toArray(new SelectItem[0]);
        //プロパティとしてセット
        setAbilityArray(tempAbilityArray);

        //////////////////////////////////////
        ///   クラス管理機能の配列
        ///////////////////////////////////////
        List<SelectItem> tempAdminClassFunctionList = new ArrayList<SelectItem>();
        tempAdminClassFunctionList.add(new SelectItem(null, "クラスの管理")); //未選択状態
        tempAdminClassFunctionList.add(new SelectItem("listclass", "クラス一覧の表示"));
        tempAdminClassFunctionList.add(new SelectItem("editclass", "新しいクラスの追加"));
        //リストから配列への変換し、プロパティにセット
        setAdminClassFunctionArray(tempAdminClassFunctionList.toArray(new SelectItem[0]));

        //////////////////////////////////////
        ///   種族管理機能の配列
        ///////////////////////////////////////
        List<SelectItem> tempAdminRaceFunctionList = new ArrayList<SelectItem>();

        tempAdminRaceFunctionList.add(new SelectItem(null, "種族の管理")); //未選択状態
        tempAdminRaceFunctionList.add(new SelectItem("listrace", "種族一覧の表示"));
        tempAdminRaceFunctionList.add(new SelectItem("editrace", "新しい種族の追加"));
        //リストから配列への変換し、プロパティにセット
        setAdminRaceFunctionArray(tempAdminRaceFunctionList.toArray(new SelectItem[0]));

        /////////////////////////////////////////////////
        // サイズの配列
        /////////////////////////////////////////////////
        List<SizeMaster> sizeFindAll = sizeMasterFacade.findAll();
        List<SelectItem> sizeSelectItemList = new ArrayList<SelectItem>();
        //未選択状態
        religionList.add(new SelectItem(null, "未選択"));
        for (SizeMaster size : sizeFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(size.getId());
            selectItem.setLabel(size.getSizeName());
            sizeSelectItemList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempSizeArray = sizeSelectItemList.toArray(new SelectItem[0]);
        //セッションBeanへのセット
        setSizeArray(tempSizeArray);
        
        /* Damage TYpe */
        damageTypeList = damageTypeMasterFacade.findAll();
        /* Arm master */
        armList = armMasterFacade.findAll();
        /* Size list */
        sizeList = sizeMasterFacade.findAll();
        /* Race List */
        raceList = raceMasterFacade.findAll();
        /* Moster List */
        monsterList = monsterMasterFacade.findAll();
        /* Moster Type List */
        typeList = typeMasterFacade.findAll();
        /* Arm Types */
        armType1List = armType1MasterFacade.findAll();
        armType2List = armType2MasterFacade.findAll();
        armType3List = armType3MasterFacade.findAll(); 
        /* alignment list */
        alignmentList = alignmentMasterFacade.findAll();
        
    }
    
    /* Size List */
    private List<SizeMaster> sizeList;

    public List<SizeMaster> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<SizeMaster> sizeList) {
        this.sizeList = sizeList;
    }
    
    /* Arm List */
    private List<ArmMaster> armList;

    public List<ArmMaster> getArmList() {
        return armList;
    }

    public void setArmList(List<ArmMaster> armList) {
        this.armList = armList;
    }
    
    public void resetArmList() 
    {
       setArmList(armMasterFacade.findAll());
    }
    
    //-------------  能力のリスト     ------------------------------
    private List<AbilityMaster> abilityList;

    public List<AbilityMaster> getAbilityList() {
        return abilityList;
    }

    public void setAbilityList(List<AbilityMaster> abilityList) {
        this.abilityList = abilityList;
    } 

    //-----------------  技能のリスト  -------------------------------
    private List<SkillMaster> skillList;

    public List<SkillMaster> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<SkillMaster> skillList) {
        this.skillList = skillList;
    }    
    
    public SkillMaster getSkillBySkillId(int skillId){
        return this.skillList.get(skillId - 1);
    }
    
    //---------------  セーブのリスト -----------------------------
    protected List<SaveMaster> saveList;

    public List<SaveMaster> getSaveList() {
        return saveList;
    }

    public void setSaveList(List<SaveMaster> saveList) {
        this.saveList = saveList;
    }    
    
    //------------- クラス設定ページ ボーナスランク選択用配列 -----------------
    private SelectItem[] rankArray;

    public SelectItem[] getRankArray() {
        return rankArray;
    }

    public void setRankArray(SelectItem[] rankArray) {
        this.rankArray = rankArray;
    }    
    
    //----------- クラス設定ページ 技能選択用配列 ------------------------
    protected SelectItem[] skillArray;

    public SelectItem[] getSkillArray() {
        return skillArray;
    }

    public void setSkillArray(SelectItem[] skillArray) {
        this.skillArray = skillArray;
    }    
    
    // ------  ボーナスランクのリスト -----------------
    protected List<BonusRankMaster> bonusRankList;

    public void setBonusRankList(List<BonusRankMaster> bonusRankList) {
        this.bonusRankList = bonusRankList;

    }

    public List<BonusRankMaster> getBonusRankList() {
        return this.bonusRankList;
    }   
    
    //------------ ダイスのリスト --------------------------
    protected List<DiceMaster> diceList;

    public List<DiceMaster> getDiceList() {
        return diceList;
    }

    public void setDiceList(List<DiceMaster> diceList) {
        this.diceList = diceList;
    }
    //----------------- ダイスの選択用配列 -------------------------
    protected SelectItem[] diceArray;

    public SelectItem[] getDiceArray() {
        return diceArray;
    }

    public void setDiceArray(SelectItem[] diceArray) {
        this.diceArray = diceArray;
    }
    //----------------- 種族の配列 --------------------------
    // このプロパティは マネージドBeanでよいかも    
    private SelectItem[] raceArray;

    public SelectItem[] getRaceArray() {
        return raceArray;
    }

    public void setRaceArray(SelectItem[] raceArray) {
        this.raceArray = raceArray;
    }
    //----------------- 種族のリスト --------------------------
    private List<RaceMaster> raceMasterList;

    public List<RaceMaster> getRaceMasterList() {
        return raceMasterList;
    }

    public void setRaceMasterList(List<RaceMaster> raceMasterList) {
        this.raceMasterList = raceMasterList;
    }
    
    //------------------- 性別の配列 -----------------------------------------------
    // このプロパティは マネージドBeanでよいかも    
    private SelectItem[] genderArray;

    public SelectItem[] getGenderArray() {
        return genderArray;
    }

    public void setGenderArray(SelectItem[] genderArray) {
        this.genderArray = genderArray;
    }    
    
    //----------------- 属性の配列 --------------------
    private SelectItem[] alignmentArray;

    public SelectItem[] getAlignmentArray() {
        return alignmentArray;
    }

    public void setAlignmentArray(SelectItem[] alignmentArray) {
        this.alignmentArray = alignmentArray;
    }    
    
    //------------------- 神格の配列 ------------------
    private SelectItem[] religionArray;

    public SelectItem[] getReligionArray() {
        return religionArray;
    }

    public void setReligionArray(SelectItem[] religionArray) {
        this.religionArray = religionArray;
    }
    
    //--------- クラス リスト----------------
    private List<ClassMaster> classMasterList;

    public List<ClassMaster> getClassMasterList() {
        return classMasterList;
    }

    public void setClassMasterList(List<ClassMaster> classMasterList) {
        this.classMasterList = classMasterList;
    }
    //--------- クラス 配列 ----------------
    private SelectItem[] classArray;

    public SelectItem[] getClassArray() {
        return classArray;
    }

    public void setClassArray(SelectItem[] classArray) {
        this.classArray = classArray;
    }
    //--------- キャンペーン配列 -------------------
    private SelectItem[] campaignArray;

    public SelectItem[] getCampaignArray() {
        return campaignArray;
    }

    public void setCampaignArray(SelectItem[] campaignArray) {
        this.campaignArray = campaignArray;
    }
    //--------- 能力値配列 -------------------
    private SelectItem[] abilityArray;

    public SelectItem[] getAbilityArray() {
        return abilityArray;
    }

    public void setAbilityArray(SelectItem[] abilityArray) {
        this.abilityArray = abilityArray;
    }
    //----- クラス管理機能の配列-----------------------
    private SelectItem[] adminClassFunctionArray;

    public SelectItem[] getAdminClassFunctionArray() {
        return adminClassFunctionArray;
    }

    public void setAdminClassFunctionArray(SelectItem[] adminClassFunctionArray) {
        this.adminClassFunctionArray = adminClassFunctionArray;
    }
    // --- 種族管理機能の配列 -------------------------
    private SelectItem[] adminRaceFunctionArray;

    public SelectItem[] getAdminRaceFunctionArray() {
        return adminRaceFunctionArray;
    }

    public void setAdminRaceFunctionArray(SelectItem[] adminRaceFunctionArray) {
        this.adminRaceFunctionArray = adminRaceFunctionArray;
    }
    /* 種族のサイズの配列 */
    protected SelectItem[] sizeArray;

    public SelectItem[] getSizeArray() {
        return sizeArray;
    }

    public void setSizeArray(SelectItem[] sizeArray) {
        this.sizeArray = sizeArray;
    }
    
    private List<DamageTypeMaster> damageTypeList;
    
    public List<DamageTypeMaster> getDamageTypeList () 
    {
     return damageTypeList;
    }
    
    private List<RaceMaster> raceList;

    public List<RaceMaster> getRaceList() {
        return raceList;
    }

    public void setRaceList(List<RaceMaster> raceList) {
        this.raceList = raceList;
    }
    
    private List<MonsterMaster> monsterList;

    public List<MonsterMaster> getMonsterList() {
        return monsterList;
    }

    public void setMonsterList(List<MonsterMaster> monsterList) {
        this.monsterList = monsterList;
    }
    
    private List<TypeMaster> typeList;

    public List<TypeMaster> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeMaster> typeList) {
        this.typeList = typeList;
    }
    
    private List<ArmType1Master> armType1List;

    public List<ArmType1Master> getArmType1List() {
        return armType1List;
    }

    public void setArmType1List(List<ArmType1Master> armType1List) {
        this.armType1List = armType1List;
    }

    private List<ArmType2Master> armType2List;
    
    public List<ArmType2Master> getArmType2List() {
        return armType2List;
    }

    public void setArmType2List(List<ArmType2Master> armType2List) {
        this.armType2List = armType2List;
    }

    private List<ArmType3Master> armType3List;    
    
    public List<ArmType3Master> getArmType3List() {
        return armType3List;
    }

    public void setArmType3List(List<ArmType3Master> armType3List) {
        this.armType3List = armType3List;
    }
    
    private List<AlignmentMaster> alignmentList;

    public List<AlignmentMaster> getAlignmentList() {
        return alignmentList;
    }

    public void setAlignmentList(List<AlignmentMaster> alignmentList) {
        this.alignmentList = alignmentList;
    }
    
}
