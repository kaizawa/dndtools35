package mbean;

import com.google.common.collect.Collections2;
import ejb.EncounterBattleMemberFacade;
import ejb.EncounterCharacterFacade;
import entity.EncounterRecord;
import mbean.util.JsfUtil;
import mbean.util.PaginationHelper;
import ejb.EncounterRecordFacade;
import entity.EncounterBattleMember;
import entity.EncounterCharacter;

import java.io.Serializable;
import java.text.StringCharacterIterator;
import java.util.*;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "encounterRecordController")
@SessionScoped
public class EncounterRecordController implements Serializable {

    @EJB
    private EncounterBattleMemberFacade encounterBattleMemberFacade;
    @EJB
    private EncounterCharacterFacade encounterCharacterFacade;
    private EncounterRecord current;
    private DataModel items = null;
    @EJB
    private EncounterRecordFacade encounterRecordFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    @ManagedProperty(value="#{scenarioRecordController}")
    private ScenarioRecordController scenarioRecordController;

    public ScenarioRecordController getScenarioRecordController() {
        return scenarioRecordController;
    }

    public void setScenarioRecordController(ScenarioRecordController scenarioRecordController) {
        this.scenarioRecordController = scenarioRecordController;
    }
    
    public EncounterRecordController() {
    }

    public EncounterRecord getSelected() {
        if (current == null) {
            current = new EncounterRecord();
            selectedItemIndex = -1;
        }
        return current;
    }

    public HtmlDataTable getEncounterCharacterTable() {
        return encounterCharacterTable;
    }

    public void setEncounterCharacterTable(HtmlDataTable encounterCharacterTable) {
        this.encounterCharacterTable = encounterCharacterTable;
    }

    public void sortBattleMember() {
        Collections.sort(battleMemberList, new Comparator<EncounterBattleMember>() {

            @Override
            public int compare(EncounterBattleMember mem1, EncounterBattleMember mem2) {

                return mem2.getInitiative().compareTo(mem1.getInitiative());
            }
        });
    }

    private EncounterRecordFacade getFacade() {
        return encounterRecordFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(100) {

                @Override
                public int getItemsCount() {
                    return getFacade().countByScenarioRecord(scenarioRecordController.getSelected());
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(
                            getFacade().findByScenarioRecordRange( 
                               scenarioRecordController.getSelected(), 
                               new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()})
                            );
                }
            };
        }
        return pagination;
    }
    
    /* 
     * encounterRecord/List は実際には表示されず、リストに相当するものは
     * ScenarioRecord/Edit に手表示される。なのでここでは null を返す。
     * (これがよばれるのは ScenarioRecord/Edit なので  null で大丈夫)
     */
    public String prepareList() {
        recreateModel();
        current = new EncounterRecord();
        selectedItemIndex = -1;
        return null;
    }

    public String prepareView() {
        current = (EncounterRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/encounterRecord/View";
    }

    public String prepareCreate() {
        current = new EncounterRecord();
        selectedItemIndex = -1;
        return null;
    }

    public String create() {
        try {
            current.setScenarioRecord(scenarioRecordController.getSelected());
            getFacade().create(current);
            JsfUtil.addSuccessMessage("エンカウンターが追加されました。");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (EncounterRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/encounterRecord/Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EncounterRecordUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (EncounterRecord) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return null;
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("削除されました");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().countByScenarioRecord(scenarioRecordController.getSelected());
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findByScenarioRecordRange( 
                               scenarioRecordController.getSelected(), 
                               new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
//        if (items == null) {
            items = getPagination().createPageDataModel();
//        }
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
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(encounterRecordFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(encounterRecordFacade.findAll(), true);
    }

    @FacesConverter(forClass = EncounterRecord.class)
    public static class EncounterRecordControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EncounterRecordController controller = (EncounterRecordController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "encounterRecordController");
            return controller.encounterRecordFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof EncounterRecord) {
                EncounterRecord o = (EncounterRecord) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EncounterRecordController.class.getName());
            }
        }
    }
    private boolean charaSelected;

    public boolean isCharaSelected() {
        EncounterCharacter encounterCharacter = (EncounterCharacter)encounterCharacterTable.getRowData();

        for (EncounterBattleMember member : encounterBattleMemberFacade.findAll()) {
            if (member.getEncounterCharacter().equals(encounterCharacter)
                    && member.getEncounterRecord().equals(current)) {
                return true;
            }
        }
        return false;
    }
    private HtmlDataTable encounterCharacterTable = new HtmlDataTable();

    /*
     * EncounterRecord のメンバーとして選択/解除する
     */
    public void setCharaSelected(boolean charaSelected) {
        int index = encounterCharacterTable.getRowIndex();

        EncounterCharacter encounterCharacter = encounterCharacterFacade.findAll().get(index);

        try {
            if (encounterCharacter != null) {
                if (charaSelected) {
                    if (encounterBattleMemberFacade.findByEncounterCharacterAndEncounterRecord(encounterCharacter,current).isEmpty()) {
                        EncounterBattleMember member = new EncounterBattleMember();
                        member.setEncounterRecord(current);
                        member.setEncounterCharacter(encounterCharacter);
                        encounterBattleMemberFacade.edit(member);
                    }
                } else {
                    List<EncounterBattleMember> memberList = encounterBattleMemberFacade.findByEncounterCharacterAndEncounterRecord(encounterCharacter,current);
                    for (EncounterBattleMember member : memberList) {
                        encounterBattleMemberFacade.remove(member);
                    }
                }
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e + "Persistence Error Occured");
        }
    }
    List<EncounterBattleMember> battleMemberList;

    public List<EncounterBattleMember> getBattleMemberList() {
        battleMemberList = encounterBattleMemberFacade.findByEncounterRecord(current);
        sortBattleMember();
        setTurn();
        return battleMemberList;
    }

    public void setTurn() {
        if (getTurnMember() == null) {
            if (battleMemberList.size() > 0) {
                EncounterBattleMember first = battleMemberList.get(0);
                first.setMyTurn(true);
                try {
                    encounterRecordFacade.edit(current);
                    encounterBattleMemberFacade.edit(first);
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Persistance Error Happened");
                }
            }
        } else {
            for (EncounterBattleMember member : battleMemberList) {
                if (member.equals(getTurnMember())) {
                    member.setMyTurn(true);
                }else { 
                    member.setMyTurn(false);
                }
                try {
                    encounterBattleMemberFacade.edit(member);
                } catch (Exception e){
                    JsfUtil.addErrorMessage("Persistance Error Happened");                    
                }
            }
            //JsfUtil.addSuccessMessage("TrunCharacter is " + getTrunMember().getEncounterCharacter().getName());
        }
    }

    public String saveBattleMembers() {
        try {
            for (EncounterBattleMember member : battleMemberList) {
                encounterBattleMemberFacade.edit(member);
            }
            JsfUtil.addSuccessMessage("Member's Poropety Updated");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Persistence Error Occured");
        }
        return null;
    }

    public void setSelectedMember(EncounterBattleMember selectedMember) {
        EncounterBattleMember member = (EncounterBattleMember) battleMemberTable.getRowData();

    }
    private HtmlDataTable battleMemberTable = new HtmlDataTable();

    public HtmlDataTable getBattleMemberTable() {
        return battleMemberTable;
    }

    public void setBattleMemberTable(HtmlDataTable battleMemberTable) {
        this.battleMemberTable = battleMemberTable;
    }
    
    private EncounterBattleMember getNextMember(Integer current_index){
        EncounterBattleMember nextMember;

        if (battleMemberList.size() <= current_index + 1) {
            nextMember = battleMemberList.get(0);                    
            // got next round
            current.setRound(current.getRound() + 1);            
        } else {
            nextMember = battleMemberList.get(current_index + 1);                                
        }        
        return nextMember;
    }

    public String nextTrun() {
        int current_index;
        current_index = battleMemberList.indexOf(getTurnMember());
        EncounterBattleMember nextMember;
        
        do{
            nextMember = getNextMember(current_index);
            current_index++;
        } while(nextMember.getHitPoint() < 0);

        getTurnMember().setMyTurn(false);
        nextMember.setMyTurn(true);        
        
        try {
            encounterRecordFacade.edit(current);
            encounterBattleMemberFacade.edit(nextMember);                
        } catch (Exception e){
            JsfUtil.addErrorMessage("Persistence Error Occured");            
        }
        return null;
    }

    public HtmlDataTable getEncounterCharacterTable2() {
        return encounterCharacterTable2;
    }

    public void setEncounterCharacterTable2(HtmlDataTable encounterCharacterTable2) {
        this.encounterCharacterTable2 = encounterCharacterTable2;
    }
    
    private HtmlDataTable encounterCharacterTable2 = new HtmlDataTable();
    
    public String resetBattle(){
        current.setRound(1);
        try {
            encounterRecordFacade.edit(current);
        } catch (Exception e){
            JsfUtil.addErrorMessage("Persistence Error Occured");                        
        }
        return null;
    }
    
    public String resetRound(){
        try {
            encounterRecordFacade.edit(current);
        } catch (Exception e){
            JsfUtil.addErrorMessage("Persistence Error Occured");                        
        }
        return null;
    }    
    
    public String setInitiativeByRandom(){
        Random rand =  new Random();
         try {
            for (EncounterBattleMember member : battleMemberList) {
                int initiativeBonus = member.getEncounterCharacter().getInitiative();

                member.setInitiative(rand.nextInt(19) + 1 + initiativeBonus);
                encounterBattleMemberFacade.edit(member);
            }
            JsfUtil.addSuccessMessage("Member's Poropety Updated");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Persistence Error Occured");
        }
        return null;
    }
    
    public EncounterRecord getCurrent(){
        return current;
    }
    
    public String getTurnCharaComments(){
        String comments = getTurnMember().getEncounterCharacter().getComments();
        return comments;
    }   
    
    public String setInitialHitPoint(){
         try {
            for (EncounterBattleMember member : battleMemberList) {
                member.setHitPoint(member.getEncounterCharacter().getHitpoint());
                encounterBattleMemberFacade.edit(member);
            }
            JsfUtil.addSuccessMessage("Member's Poropety Updated");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Persistence Error Occured");
        }
        return null;
    }    

    private Integer hpModifier;

    public Integer getHpModifier() {
        return 0;
    }

    public void setHpModifier(Integer mod) {
        int index = battleMemberTable.getRowIndex();
        EncounterBattleMember member = battleMemberList.get(index);
        member.setHitPoint(member.getHitPoint() - mod);

    }
    
    public String getHpColor(){
        EncounterBattleMember member = (EncounterBattleMember)battleMemberTable.getRowData();
        if(member.getHitPoint() > 0){
            return "black;";
        } else {
            return "red;";
        }
    }
    
    public String reread(){
        items = null;
        return null;
    }       
    
    public EncounterBattleMember getTurnMember(){
        List<EncounterBattleMember> memberList = encounterBattleMemberFacade.findByEncounterRecord(current);
        
        for(EncounterBattleMember member : memberList){
            if(member.getMyTurn())
                return member;
        }
        return memberList.get(0);
    }
}
