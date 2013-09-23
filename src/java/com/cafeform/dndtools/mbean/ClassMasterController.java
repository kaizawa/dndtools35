/*
 * ClassListPage.java
 *
 * Created on 2009/01/05, 23:14:53
 */

package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.ClassSkillMaster;
import com.cafeform.dndtools.entity.ClassSkillMasterPK;
import com.cafeform.dndtools.entity.BonusRankMaster;
import com.cafeform.dndtools.entity.ClassSaveMaster;
import com.cafeform.dndtools.entity.SkillMaster;
import com.cafeform.dndtools.entity.ClassMaster;
import com.cafeform.dndtools.ejb.ClassMasterFacade;
import com.cafeform.dndtools.ejb.ClassSkillMasterFacade;
import com.cafeform.dndtools.ejb.SkillMasterFacade;
import com.cafeform.dndtools.ejb.ClassSaveMasterFacade;
import com.cafeform.dndtools.ejb.DiceMasterFacade;
import com.cafeform.dndtools.ejb.BonusRankMasterFacade;
import com.cafeform.dndtools.ejb.SaveMasterFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

import javax.faces.bean.ManagedProperty;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import com.cafeform.dndtools.mbean.util.JsfUtil;
import javax.inject.Named;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author ka78231
 */

@Named
@SessionScoped
public class ClassMasterController implements Serializable {
   
    
    @Inject
    private SessionController sessionController;

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }
    
    @Inject
    private ApplicationController applicationController;

    public ApplicationController getApplicationController() {
        return applicationController;
    }

    public void setApplicationController(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }
    
    @Inject
    private DiceMasterFacade diceMasterFacade;
    @Inject
    private SaveMasterFacade saveMasterFacade;
    @Inject
    private ClassSaveMasterFacade classSaveMasterFacade;
    @Inject
    private ClassSkillMasterFacade classSkillMasterFacade;
    @Inject
    private SkillMasterFacade skillMasterFacade;
    @Inject
    private ClassMasterFacade classMasterFacade;
    @Inject
    private BonusRankMasterFacade bonusRankMasterFacade;

    private HtmlDataTable classSkillTable = new HtmlDataTable();

    public HtmlDataTable getClassSkillTable() {
        return classSkillTable;
    }

    public void setClassSkillTable(HtmlDataTable hdt) {
        this.classSkillTable = hdt;
    }
    private HtmlDataTable classSkillTable1 = new HtmlDataTable();

    public HtmlDataTable getClassSkillTable1() {
        return classSkillTable1;
    }

    public void setClassSkillTable1(HtmlDataTable hdt) {
        this.classSkillTable1 = hdt;
    }
    
    private ClassMaster classMaster;

    public ClassMaster getClassMaster() {
        return classMaster;
    }

    public void setClassMaster(ClassMaster classMaster) {
        this.classMaster = classMaster;
    }


    public void initProperty() {
        
        ClassMaster klass =  getClassMaster();

        if (klass.getId() != null) {
            // すでに存在するクラスの場合

            /*
             * このクラスのクラス技能一覧の取得
             */
            List<ClassSkillMaster> tempClassSkillList = classSkillMasterFacade.findByClass(klass);
            setClassSkillList(tempClassSkillList);
            /*
             * このクラスのセーブ一覧の取得
             */
            List<ClassSaveMaster> tempClassSaveMasterList = classSaveMasterFacade.findByClass(klass);
            setClassSaveMasterList(tempClassSaveMasterList);
            /*
             * このクラスの基本攻撃ボーナスの取得
             */
            if (klass.getBaseAttackRankId() != null) {
                setClassEditSelectedBabRank(klass.getBaseAttackRankId().getId());
            } else {
                setClassEditSelectedBabRank(null);
            }
            /*
             * セーヴランクの取得
             */
            setClassSaveFortitute(classSaveMasterFacade.findByClassAndSave(klass, saveMasterFacade.find(DnDUtil.FORTITUTE)));
            setClassSaveReflex(classSaveMasterFacade.findByClassAndSave(klass, saveMasterFacade.find(DnDUtil.REFLEX)));
            setClassSaveWill(classSaveMasterFacade.findByClassAndSave(klass, saveMasterFacade.find(DnDUtil.WILL)));
            setClassEditSelectedFortituteRank(getClassSaveFortitute().getRankId().getId());
            setClassEditSelectedReflexRank(getClassSaveReflex().getRankId().getId());
            setClassEditSelectedWillRank(getClassSaveWill().getRankId().getId());
        } else {
            setClassSkillList(new ArrayList<ClassSkillMaster>());
            setClassSaveMasterList(new ArrayList<ClassSaveMaster>());
            setClassEditSelectedBabRank(null);
            setClassEditSelectedFortituteRank(null);
            setClassEditSelectedReflexRank(null);
            setClassEditSelectedWillRank(null);
        }
        /*
         * このクラスのヒットダイスの種類の取得
         */
        if (klass.getHitDiceType() != null) {
            setClassEditSelectedHitDiceType(klass.getHitDiceType().getId());
        } else {
            setClassEditSelectedHitDiceType(null);
        }
    }

    public String classListButton_action() {
        return "/classMaster/ClassListPage";
    }

    public String saveButton_action() {        
        if(getSessionController().isLoggedIn() == false){
            getSessionController().setTargetPage("/classMaster/EditClassPage");
            return "/login/LoginPage";
        }
        ClassMaster klass =  getClassMaster();

        BonusRankMaster rank = bonusRankMasterFacade.find(getClassEditSelectedBabRank());
        klass.setBaseAttackRankId(rank);

        klass.setHitDiceType(diceMasterFacade.find(getClassEditSelectedHitDiceType()));


        try {
            if (klass.getId() == null) {
                //新規 クラス・マスターを作成
                classMasterFacade.create(klass);
                //クラスのセーヴマスター作成
                ClassSaveMaster fortitute = new ClassSaveMaster(klass.getId(), DnDUtil.FORTITUTE);
                ClassSaveMaster refrex = new ClassSaveMaster(klass.getId(), DnDUtil.REFLEX);
                ClassSaveMaster will = new ClassSaveMaster(klass.getId(), DnDUtil.WILL);
                fortitute.setRankId(bonusRankMasterFacade.find(getClassEditSelectedFortituteRank()));
                refrex.setRankId(bonusRankMasterFacade.find(getClassEditSelectedReflexRank()));
                will.setRankId(bonusRankMasterFacade.find(getClassEditSelectedWillRank()));
                classSaveMasterFacade.create(fortitute);
                classSaveMasterFacade.create(refrex);
                classSaveMasterFacade.create(will);
                //クラスの技能マスター作成
                for(ClassSkillMaster classSkill : getClassSkillList()){
                    // クラス技能リストにクラスがあったとしても、まだクラスIDはセットされていないはずなので、ここでセット
                    classSkill.getClassSkillMasterPK().setClassId(klass.getId());
                    classSkillMasterFacade.create(classSkill);
                }
                JsfUtil.addSuccessMessage("作成しました");
            } else {
                if (getClassSaveFortitute() != null) {
                    classSaveMasterFacade.edit(getClassSaveFortitute());
                }
                if (getClassSaveReflex() != null) {
                    classSaveMasterFacade.edit(classSaveReflex);
                }
                if (getClassSaveReflex() != null) {
                    classSaveMasterFacade.edit(classSaveWill);
                }
                for(ClassSkillMaster classSkillOld : classSkillMasterFacade.findByClass(klass)){
                    for (ClassSkillMaster classSkillNew : getClassSkillList()) {
                        if(classSkillNew.getClassSkillMasterPK().getSkillId() == classSkillOld.getClassSkillMasterPK().getSkillId()){
                            continue;
                        }
                    }
                    classSkillMasterFacade.remove(classSkillOld);
                }
                for(ClassSkillMaster classSkillNew : getClassSkillList()){
                    for(ClassSkillMaster classSkillOld : classSkillMasterFacade.findByClass(klass)){
                        if(classSkillNew.getClassSkillMasterPK().getSkillId() == classSkillOld.getClassSkillMasterPK().getSkillId()){
                            continue;
                        }                        
                    } 
                    classSkillMasterFacade.create(classSkillNew);
                }
                //更新
                classMasterFacade.edit(klass);
                JsfUtil.addSuccessMessage("保存しました");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JsfUtil.addErrorMessage("クラスの保存に失敗しました");
            return null;
        }

        /*
         * 選択メニュー用クラスの配列とリストを再作成し、ApplicationController のプロパティにセット
         */

        List<ClassMaster> classFindAll = classMasterFacade.findAll();
        List<SelectItem> classList = new ArrayList<SelectItem>();
        //未選択状態
        classList.add(new SelectItem(null, "未選択"));
        for (ClassMaster classItem : classFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(classItem.getId());
            selectItem.setLabel(classItem.getClassName());
            classList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempClassArray = classList.toArray(new SelectItem[0]);
        //セッションBEANへのセット
        getApplicationController().setClassArray(tempClassArray);
        return null;
    }
    /*
     * 基本攻撃ボーナス
     */
    private Integer classEditselectedBabRank;

    public Integer getClassEditSelectedBabRank() {
        return classEditselectedBabRank;
    }

    public void setClassEditSelectedBabRank(Integer classEditselectedBabRank) {
        this.classEditselectedBabRank = classEditselectedBabRank;
    }

    //   頑健
    private Integer classEditselectedFortituteRank;

    public Integer getClassEditSelectedFortituteRank() {
        return classEditselectedFortituteRank;
    }

    public void setClassEditSelectedFortituteRank(Integer classEditselectedFortituteRank) {
        this.classEditselectedFortituteRank = classEditselectedFortituteRank;
    }
    //   反応
    private Integer classEditselectedReflexRank;

    public Integer getClassEditSelectedReflexRank() {
        return classEditselectedReflexRank;
    }

    public void setClassEditSelectedReflexRank(Integer classEditselectedReflexRank) {
        this.classEditselectedReflexRank = classEditselectedReflexRank;
    }
    //   意思
    private Integer classEditselectedWillRank;

    public Integer getClassEditSelectedWillRank() {
        return classEditselectedWillRank;
    }

    public void setClassEditSelectedWillRank(Integer classEditselectedWillRank) {
        this.classEditselectedWillRank = classEditselectedWillRank;
    }

    /*
     * クラス技能一覧
     */
    private List<ClassSkillMaster> classSkillList;

    public List<ClassSkillMaster> getClassSkillList() {
        return classSkillList;
    }

    public void setClassSkillList(List<ClassSkillMaster> classSkillList) {
        this.classSkillList = classSkillList;
    }

    /*
     * 表からの技能名
     */
    private String skillNameBySkillId;

    public String getSkillNameBySkillId() {
        SkillMaster skill = (SkillMaster) classSkillTable.getRowData();
        if (skill == null) {
            return null;
        }
        return skill.getSkillName();
    }
    /*
     * 表からの技能対応能力値
     */

    public String getAbilityNameBySkillId() {
        SkillMaster skill = (SkillMaster) classSkillTable.getRowData();
        if (skill == null) {
            return "技能取得不可。バグ?";
        }
        return skill.getAbilityId().getAbilityName();
    }

    /*
     * クラス
     */
    public boolean isSelectedClassSkill() {
        SkillMaster skill = (SkillMaster) classSkillTable.getRowData();
        List<ClassSkillMaster> classSkillListTemp = getClassSkillList();
        if (classSkillListTemp == null) {
            return false;
        }
        for (ClassSkillMaster classSkill : classSkillListTemp) {
            if (classSkill.getClassSkillMasterPK().getSkillId() == skill.getId()) {
                return true;
            }
        }
        return false;
    }

    public void setSelectedClassSkill(boolean set) {
        SkillMaster skill = (SkillMaster) classSkillTable.getRowData();
        List<ClassSkillMaster> classSkillListTemp = getClassSkillList();

        //既存のクラス技能のリストを巡る
        for (ClassSkillMaster classSkill : classSkillListTemp) {
                if (classSkill.getClassSkillMasterPK().getSkillId() == skill.getId()) {
                    //リストされている
                    if (set) {
                        //変更無し
                    } else {
                        //クラス技能から除外された
                        classSkillListTemp.remove(classSkill);
                        setClassSkillList(classSkillListTemp);
                    }
                    return;
                }
        }

        //リストになく、且つ今回も未設定
        if (!set) {
            return;
        }

        //ここにくるのはリストに載ってなくて、新たに追加されるとき
        ClassMaster klass =  getClassMaster();
        ClassSkillMaster newClassSkill =  new ClassSkillMaster(new ClassSkillMasterPK());
        newClassSkill.getClassSkillMasterPK().setSkillId(skill.getId());
        if(klass.getId() != null )
            newClassSkill.getClassSkillMasterPK().setClassId(klass.getId().intValue());
        classSkillListTemp.add(newClassSkill);
        setClassSkillList(classSkillListTemp);
    }
    
    protected List<ClassSaveMaster> classSaveMasterList;

    public List<ClassSaveMaster> getClassSaveMasterList() {
        return classSaveMasterList;
    }

    public void setClassSaveMasterList(List<ClassSaveMaster> classSaveMasterList) {
        this.classSaveMasterList = classSaveMasterList;
    }
    ClassSaveMaster classSaveFortitute;

    public ClassSaveMaster getClassSaveFortitute() {
        return classSaveFortitute;
    }

    public void setClassSaveFortitute(ClassSaveMaster classSaveFortitute) {
        this.classSaveFortitute = classSaveFortitute;
    }
    ClassSaveMaster classSaveReflex;

    public ClassSaveMaster getClassSaveReflex() {
        return classSaveReflex;
    }

    public void setClassSaveReflex(ClassSaveMaster classSaveReflex) {
        this.classSaveReflex = classSaveReflex;
    }
    ClassSaveMaster classSaveWill;

    public ClassSaveMaster getClassSaveWill() {
        return classSaveWill;
    }

    public void setClassSaveWill(ClassSaveMaster classSaveWill) {
        this.classSaveWill = classSaveWill;
    }

    public void dropdown2_processValueChange(ValueChangeEvent vce) {
    }

    /*
     * ヒットダイス
     */
    private Integer classEditselectedHitDiceType;

    public Integer getClassEditSelectedHitDiceType() {
        return classEditselectedHitDiceType;
    }

    public void setClassEditSelectedHitDiceType(Integer classEditselectedHitDiceType) {
        this.classEditselectedHitDiceType = classEditselectedHitDiceType;
    }

    public String deleteButton_action() {
        try {
            classMasterFacade.remove(getClassMaster());
        } catch (Exception ex) {
            ex.printStackTrace();
            JsfUtil.addErrorMessage("削除に失敗しました");
            return null;
        }
        return "/classMaster/ClassListPage";
    }
    
    /*
     * 削除ボタンの表示
     */
    public boolean isDeleteButtonDisabled() {
        return ( getClassMaster() == null || getClassMaster().getId() == null);
    }

    public String cancelButton_action() {
        return "/classMaster/ClassListPage";
    }    

    private HtmlDataTable classTable = new HtmlDataTable();

    public HtmlDataTable getClassTable() {
        return classTable;
    }

    public void setClassTable(HtmlDataTable hdt) {
        this.classTable = hdt;
    }
    private HtmlDataTable classTable1 = new HtmlDataTable();

    public HtmlDataTable getClassTable1() {
        return classTable1;
    }

    public void setClassTable1(HtmlDataTable hdt) {
        this.classTable1 = hdt;
    }

    
    List<ClassMaster> classMasterList;

    public List<ClassMaster> getClassMasterList() {
        return classMasterFacade.findAll();
    }

    public String editClassLink_action() {
        int classid = classTable.getRowIndex();
        ClassMaster classmaser = getClassMasterList().get(classid);
        setClassMaster(classmaser);
        initProperty();
        
        return prepareEdit();
    }

    public String newClassButton_action() {
        ClassMaster newClassMaster = new ClassMaster();
        setClassMaster(newClassMaster);

        return prepareEdit();
    }
     
    public String prepareEdit() {
        initProperty();
        if(getSessionController().isLoggedIn() == false){
            getSessionController().setTargetPage("/classMaster/EditClassPage");
            return "/login/LoginPage";
        }
        return "/classMaster/EditClassPage";
    }
}