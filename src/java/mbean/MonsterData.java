package mbean;

import com.sun.j3d.loaders.ParsingErrorException;
import entity.MonsterMaster;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * データベース上のマスターデータから算出できるデータを含む、モンスターのあらゆる を報告できるクラス。MOSTTER_MASTER 表を表す
 * MonsterMaster クラスの インスタンスを保持する。
 *
 * @author kaizawa
 */
public class MonsterData implements CharacterSummary {

    private MonsterMaster monster;
    int hitpoint = 0;
    boolean randomHitPoint;

    public MonsterData(MonsterMaster monsterMaster) {
        this(monsterMaster, false);
    }

    public MonsterData(MonsterMaster monsterMaster, boolean randomHitPoint) {
        this.monster = monsterMaster;
        this.randomHitPoint = randomHitPoint;
    }
    
    /**
     * MonsterMaster の配列から MosterData の配列を作る。Database 上のモンスター データのリストを印字ように加工した
     * MonsterData のリストに変換する。
     *
     * @param masterList
     * @return
     */
    public static List<MonsterData> getMonsterDataListFromMaster(List<MonsterMaster> masterList) {
        List<MonsterData> dataList = new ArrayList<MonsterData>();
        for (MonsterMaster master : masterList) {
            dataList.add(new MonsterData(master));
        }
        return dataList;
    }

    @Override
    public String getAbilities() {
        return "not implemented yet";
    }

    @Override
    public String getArmorClass() {
        return monster.getAc() + "、接触" + monster.getAcTouch() + "、立ちすくみ" + monster.getAcFlatFooted();
    }

    @Override
    public String getNormalAttackDescription() {
        return monster.getAttack();
    }

    @Override
    public Integer getCharangingRate() {
        return monster.getChallengeRating();
    }

    @Override
    public String getEnvironment() {
        return monster.getEnvironment();
    }

    @Override
    public String getFeats() {
        return "not implemented yet";
    }

    @Override
    public String getFullAttackDescription() {
        return "not implemented yet";
    }

    @Override
    public String getHitDice() {
        return monster.getHitDiceNum() + monster.getHitDiceType().getName();
    }

    @Override
    public Integer getHitPoint() {
        if (hitpoint == 0) {
            if (randomHitPoint) {
                setRandomHitPoint();
            } else {
                setAverageHitPoint();
            }
        }
        return hitpoint;
    }

    public void setAverageHitPoint() {
        Integer hitdice = monster.getHitDiceType().getType();
        Integer diceNum = monster.getHitDiceNum();
        Float tempNum = 0.0F;
        for (int i = 1 ; i < hitdice + 1 ; i++) {
            tempNum += i;
        }
        hitpoint= (int )((tempNum / hitdice.floatValue()) * diceNum.floatValue());
    }

    public void setRandomHitPoint() {
        int newhitpoint = 0;
        int hitdice = monster.getHitDiceType().getType();
        int diceNum = monster.getHitDiceNum();
        for (int i = 0; i < diceNum; i++) {
            newhitpoint += new Random().nextInt(hitdice) + 1;
        }
        hitpoint = newhitpoint;
    }

    @Override
    public Integer getInitiative() {
        return monster.getInitiative();
    }

    @Override
    public String getKlass() {
        return "not implemented yet";
    }

    @Override
    public String getName() {
        return monster.getName();
    }

    @Override
    public String getOrganization() {
        return monster.getOrganization();
    }

    @Override
    public String getContactSpaceAndReach(){
        return monster.getSizeId().getContactSpace() 
                + "フィート/" 
                + monster.getReach()
                + "フィート";
    }

    @Override
    public String getSave() {
        return "not implemented yet";
    }

    private String getSize() {
        return monster.getSizeId().getSizeName();
    }

    @Override
    public String getSkills() {
        return "not implemented yet";
    }

    @Override
    public String getSpecialAttack() {
        return monster.getSpecialAttacks();
    }

    @Override
    public String getSpecialQualities() {
        return monster.getSpecialQualities();
    }

    @Override
    public String getSpeed() {
        StringBuilder str = new StringBuilder();
        str.append(monster.getSpeed());
                str.append("フィート(");
                str.append((int)monster.getSpeed()/5);
                str.append("マス)");
                return str.toString();
    }

    private String getType() {
        return monster.getType().getName();
    }

    @Override
    public String getBaseAttackAndGrapple() {
        StringBuilder str = new StringBuilder();
        str.append(monster.getGrapple() < 0 ? "-" : "+");
        str.append(monster.getBaseAttack());
        str.append("/");
        str.append(monster.getBaseAttack() < 0 ? "-" : "+");
        str.append(monster.getGrapple().toString());
        return str.toString();
    }

    @Override
    public String getSizeAndType() {
        return getSize() + "サイズの" + getType();
    }


}
