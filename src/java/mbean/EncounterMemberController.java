package mbean;

import entity.EncounterMember;
import mbean.util.JsfUtil;
import mbean.util.PaginationHelper;
import ejb.EncounterMemberFacade;
import ejb.EncounterRecordFacade;
import ejb.ScenarioCharacterRecordFacade;
import entity.EncounterRecord;
import entity.ScenarioCharacterRecord;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

@ManagedBean(name = "encounterMemberController")
@SessionScoped
public class EncounterMemberController implements Serializable {
    @EJB
    private ScenarioCharacterRecordFacade scenarioCharacterRecordFacade;

    public ScenarioCharacterRecordFacade getScenarioCharacterRecordFacade() {
        return scenarioCharacterRecordFacade;
    }

    public void setScenarioCharacterRecordFacade(ScenarioCharacterRecordFacade scenarioCharacterRecordFacade) {
        this.scenarioCharacterRecordFacade = scenarioCharacterRecordFacade;
    }
    @EJB
    private EncounterRecordFacade encounterRecordFacade;

    public EncounterMemberFacade getEncounterMemberFacade() {
        return encounterMemberFacade;
    }

    public void setEncounterMemberFacade(EncounterMemberFacade encounterMemberFacade) {
        this.encounterMemberFacade = encounterMemberFacade;
    }

    public EncounterRecordFacade getEncounterRecordFacade() {
        return encounterRecordFacade;
    }

    public void setEncounterRecordFacade(EncounterRecordFacade encounterRecordFacade) {
        this.encounterRecordFacade = encounterRecordFacade;
    }
    
    @ManagedProperty(value="#{encounterRecordController}")
    private EncounterRecordController encounterRecordController;

    public EncounterRecordController getEncounterRecordController() {
        return encounterRecordController;
    }

    public void setEncounterRecordController(EncounterRecordController encounterRecordController) {
        this.encounterRecordController = encounterRecordController;
    }

    
    @EJB
    private EncounterMemberFacade encounterMemberFacade;

    
    public HtmlDataTable getEncounterMemberTable() {
        return encounterMemberTable;
    }

    public void setEncounterMemberTable(HtmlDataTable encounterCharacterTable) {
        this.encounterMemberTable = encounterCharacterTable;
    }

    public void sortEncounterMember() {
        Collections.sort(encounterMemberList, new Comparator<EncounterMember>() {

            @Override
            public int compare(EncounterMember mem1, EncounterMember mem2) {

                return mem2.getInitiative().compareTo(mem1.getInitiative());
            }
        });
    }

    public void setHpModifier(Integer mod) {
        int index = encounterMemberTable.getRowIndex();
        EncounterMember member = encounterMemberList.get(index);
        ScenarioCharacterRecord chara = member.getScenarioCharacterRecord();
        chara.setHitPoint(member.getScenarioCharacterRecord().getHitPoint() - mod);
    }
    
    public String getHpColor(){
        EncounterMember member = (EncounterMember)encounterMemberTable.getRowData();
        if(member.getScenarioCharacterRecord().getHitPoint() > 0){
            return "black;";
        } else {
            return "red;";
        }
    } 
    
    private HtmlDataTable encounterMemberTable = new HtmlDataTable();        
 
    private List<EncounterMember> encounterMemberList;

    public List<EncounterMember> getEncounterMemberList() {
        encounterMemberList = encounterMemberFacade.findByEncounterRecord(encounterRecordController.getCurrent());
        if(encounterMemberList.size() > 0){
            sortEncounterMember();
            setTurn();
        }
        return encounterMemberList;
    }

    public void setTurn() {
        if (getTurnMember() == null) {
            if (encounterMemberList.size() > 0) {
                EncounterMember first = encounterMemberList.get(0);
                first.setMyTurn(true);
                try {
                    encounterRecordFacade.edit(encounterRecordController.getCurrent());
                    encounterMemberFacade.edit(first);
                } catch (Exception e) {
                    JsfUtil.addErrorMessage("Persistance Error Happened");
                }
            }
        } else {
            for (EncounterMember member : encounterMemberList) {
                if (member.equals(getTurnMember())) {
                    member.setMyTurn(true);
                }else { 
                    member.setMyTurn(false);
                }
                try {
                    encounterMemberFacade.edit(member);
                } catch (Exception e){
                    JsfUtil.addErrorMessage("Persistance Error Happened");                    
                }
            }
            //JsfUtil.addSuccessMessage("TrunCharacter is " + getTrunMember().getEncounterCharacter().getName());
        }
    }

    public String saveEncounterMembers() {
        try {
            for (EncounterMember member : encounterMemberList) {
                ScenarioCharacterRecord chara =  member.getScenarioCharacterRecord();
                scenarioCharacterRecordFacade.edit(chara);
                encounterMemberFacade.edit(member);
            }
            JsfUtil.addSuccessMessage("Member's Poropety Updated");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Persistence Error Occured");
        }
        return null;
    }

    public void setSelectedMember(EncounterMember selectedMember) {
        EncounterMember member = (EncounterMember) encounterMemberTable.getRowData();

    }

    public EncounterMember getNextMember(Integer current_index){
        EncounterMember nextMember;

        if (encounterMemberList.size() <= current_index + 1) {
            nextMember = encounterMemberList.get(0);                    
            // got next round
            encounterRecordController.getCurrent().setRound(encounterRecordController.getCurrent().getRound() + 1);            
        } else {
            nextMember = encounterMemberList.get(current_index + 1);                                
        }        
        return nextMember;
    }
    
    public EncounterMember getPreviousMember(Integer current_index) throws NoPreviousMemberException{
        EncounterMember prevMember;
        int current_round = encounterRecordController.getCurrent().getRound();

        if (current_index == 0) {
            if(current_round == 1){
                // It's first rounds's first member.
                throw new NoPreviousMemberException();
            }
            prevMember = encounterMemberList.get(encounterMemberList.size() -1);            
            // got previous round
            encounterRecordController.getCurrent().setRound(current_round - 1);            
        } else {
            prevMember = encounterMemberList.get(current_index - 1);                                
        }        
        return prevMember;
    }

    public String nextTurn() {
        int current_index;
        EncounterMember currentMember = getTurnMember();

        if(currentMember == null)
            return null;
    
        current_index = encounterMemberList.indexOf(currentMember);
        EncounterMember nextMember;
        
        do{
            nextMember = getNextMember(current_index);
            current_index++;
        } while(nextMember.getScenarioCharacterRecord().getHitPoint() < 0);

        currentMember.setMyTurn(false);
        nextMember.setMyTurn(true);        
        
        try {
            encounterRecordFacade.edit(encounterRecordController.getCurrent());
            encounterMemberFacade.edit(currentMember);       
            encounterMemberFacade.edit(nextMember);                

        } catch (Exception e){
            JsfUtil.addErrorMessage("Persistence Error Occured");            
        }
        return null;
    }
    
        public String previousTurn() {
        int current_index;
        EncounterMember currentMember = getTurnMember();

        if(currentMember == null)
            return null;
    
        current_index = encounterMemberList.indexOf(currentMember);
        EncounterMember prevMember = null;

        do{
            try {
                prevMember = getPreviousMember(current_index);
                if(current_index == 0){
                    current_index = encounterMemberList.size() - 1;
                }
                current_index--;
            } catch (NoPreviousMemberException e){
                JsfUtil.addErrorMessage("最初のターンです。これ以上戻れません。");
                return null;
            }
        } while(prevMember.getScenarioCharacterRecord().getHitPoint() < 0);

        currentMember.setMyTurn(false);
        prevMember.setMyTurn(true);        
        
        try {
            encounterRecordFacade.edit(encounterRecordController.getCurrent());
            encounterMemberFacade.edit(currentMember);       
            encounterMemberFacade.edit(prevMember);                

        } catch (Exception e){
            JsfUtil.addErrorMessage("Persistence Error Occured");            
        }
        return null;
    }
    

    /** 
     * Player Character 以外(モンスター)のイニシアチブをランダム値に設定
     */
    public String setInitiativeByRandom(){
        Random rand =  new Random();
         try {
             for (EncounterMember member : encounterMemberList) {
                 if(member.getScenarioCharacterRecord().isPlayerCharacter() == false ){
                     int initiativeBonus = member.getScenarioCharacterRecord().getInitiative();
                     member.setInitiative(rand.nextInt(19) + 1 + initiativeBonus);
                     encounterMemberFacade.edit(member);
                 }
            }
            JsfUtil.addSuccessMessage("Member's Poropety Updated");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Persistence Error Occured");
        }
        return null;
    }
    
    public EncounterMember getTurnMember(){
        List<EncounterMember> memberList = encounterMemberFacade.findByEncounterRecord(encounterRecordController.getCurrent());
        
        for(EncounterMember member : memberList){
            if(member.getMyTurn())
                return member;
        }
        if (memberList.isEmpty())
            return null;
                    
        return memberList.get(0);
    }
    
    public String decommission(){
        EncounterMember member = (EncounterMember) encounterMemberTable.getRowData();
        
        encounterMemberFacade.remove(member);
        return null;
    }

    public Integer getHpModifier() {
        return 0;
    }    
    
    public void setTurnToFirstMember(){
        EncounterMember currentMember = getTurnMember();
        EncounterMember firstMember = encounterMemberList.get(0);
        
        currentMember.setMyTurn(false);
        firstMember.setMyTurn(true);        
        
        try {
            encounterRecordFacade.edit(encounterRecordController.getCurrent());
            encounterMemberFacade.edit(currentMember);       
            encounterMemberFacade.edit(firstMember);                

        } catch (Exception e){
            JsfUtil.addErrorMessage("Persistence Error Occured");            
        }
    }

    
    public String resetBattle() {
        encounterRecordController.resetBattle();
        return resetRound();
    }

    public String resetRound() {
        setTurnToFirstMember();        
        return null;
    }
    
    public String setInitialHitPoint(){
         try {
             for (EncounterMember member : encounterMemberList) {
                 int initialHp = 0;
                 String regxp = "\\d+d\\d+\\((\\d+)hp\\)";
                 Pattern pattern = Pattern.compile(regxp);
                 Matcher matcher = pattern.matcher(member.getScenarioCharacterRecord().getHitDice());
                 if(matcher.find()){
                     initialHp = Integer.parseInt(matcher.group(1));
                 } 
                 ScenarioCharacterRecord chara = member.getScenarioCharacterRecord();
                 chara.setHitPoint(initialHp);    
                 scenarioCharacterRecordFacade.edit(chara);
                 encounterMemberFacade.edit(member);
            }
            JsfUtil.addSuccessMessage("Member's Poropety Updated");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Persistence Error Occured");
        }
        return null;
    }
}
