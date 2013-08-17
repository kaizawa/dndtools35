/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entity.DiceMaster;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

import javax.faces.component.html.HtmlDataTable;

/**
 *
 * @author kaizawa
 */

@SessionScoped
public class DiceToolsController implements Serializable {
    //ダイスタイプ
    private DiceMaster selectedDice = null;

    public DiceMaster getSelectedDice() {
        return selectedDice;
    }

    public void setSelectedDice(DiceMaster selectedDice) {
        this.selectedDice = selectedDice;
    }
    
    // ダイス数
    private Integer selectedDiceNumber = 0;
    
    public Integer getSelectedDiceNumber() {

        return selectedDiceNumber;
    }

    public void setSelectedDiceNumber(Integer selectedDiceNumber) {

        this.selectedDiceNumber = selectedDiceNumber;
    }
    
    public HtmlDataTable getDiceTable() {
        return diceTable;
    }

    public void setDiceTable(HtmlDataTable diceTable) {
        this.diceTable = diceTable;
    }

    private HtmlDataTable diceTable = new HtmlDataTable();

    public DiceToolsController() {
    }
    
    Integer getAverage(Integer dice){
        int sum = 0;
        int i;

        for (i = 1 ; i > dice ; i++){
            sum += i;
        }
        return sum / dice;
    }
    
    public Integer getRandomNumber(){
        if(selectedDice == null){
            return 0;
        }
        return new Random().nextInt(selectedDice.getType()) + 1;
    }

    public String getRandomNumberListText(){
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        Integer total = 0;
        Integer num = 0;
        
        if(selectedDice == null){
            return "-";
        }
        
        for(int i = 0 ; i < selectedDiceNumber ; i ++){
            num = rand.nextInt(selectedDice.getType()) + 1;            
            if(i != 0) {
                sb.append(" + ");
            }
            total += num;
            sb.append(num);
        }        
        
        sb.append(" = " + total);
        
        return sb.toString();
    } 
    
    List<Integer> numberList = null;
    
    public List<Integer> getNumberList(){
        if( numberList == null){
            numberList = new ArrayList<Integer>();
            for(Integer i = 1 ; i < 21 ; i++){
                numberList.add(i);
            }
        }
        return numberList;
    }
}
