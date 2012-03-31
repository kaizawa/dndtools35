/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.CharacterEquipmentFacade;
import ejb.RaceMasterFacade;
import ejb.AbilityMasterFacade;
import ejb.CharacterGrowthRecordFacade;
import ejb.CharacterAbilityRecordFacade;
import ejb.CharacterSaveRecordFacade;
import ejb.CharacterRecordFacade;
import ejb.GenderMasterFacade;
import ejb.AlignmentMasterFacade;
import ejb.CharacterSkillRecordFacade;
import ejb.SkillSynergyMasterFacade;
import ejb.ClassSaveMasterFacade;
import ejb.ClassMasterFacade;
import ejb.ClassSkillMasterFacade;
import ejb.SaveMasterFacade;
import ejb.RaceAbilityMasterFacade;
import ejb.ReligionMasterFacade;
import ejb.SkillMasterFacade;
import ejb.CharacterSkillGrowthRecordFacade;
import entity.*;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CharacterData implements CharacterSummary {

    InitialContext ctx;
    FacesContext context = FacesContext.getCurrentInstance();
    private SkillSynergyMasterFacade skillSynergyMasterFacade;
    private CharacterEquipmentFacade characterEquipmentFacade;
    private CharacterSaveRecordFacade characterSaveRecordFacade;
    private ClassSaveMasterFacade classSaveMasterFacade;
    private RaceAbilityMasterFacade raceAbilityMasterFacade;
    private CharacterSkillRecordFacade characterSkillRecordFacade;
    private CharacterAbilityRecordFacade characterAbilityRecordFacade;
    private SaveMasterFacade saveMasterFacade;
    private SkillMasterFacade skillMasterFacade1;
    private ClassSkillMasterFacade classSkillMasterFacade;
    protected ClassMasterFacade classMasterFacade;
    protected CharacterSkillGrowthRecordFacade characterSkillGrowthRecordFacade;
    protected SkillMasterFacade skillMasterFacade;
    protected ReligionMasterFacade religionMasterFacade;
    protected AlignmentMasterFacade alignmentMasterFacade;
    protected AbilityMasterFacade abilityMasterFacade;
    protected GenderMasterFacade genderMasterFacade;
    protected RaceMasterFacade raceMasterFacade;
    protected CharacterGrowthRecordFacade characterGrowthRecordFacade;
    protected CharacterRecordFacade characterRecordFacade;
    private CharacterRecord characterRecord;

    public CharacterRecord getCharacterRecord() {
        return characterRecord;
    }

    private CharacterData() {
    }

    public CharacterData(CharacterRecord characterRecord) {
        this.characterRecord = characterRecord;
        try {
            ctx = new InitialContext();
            abilityMasterFacade = (AbilityMasterFacade) ctx.lookup("java:module/AbilityMasterFacade");
            skillSynergyMasterFacade = (SkillSynergyMasterFacade) ctx.lookup("java:module/SkillSynergyMasterFacade");
            characterEquipmentFacade = (CharacterEquipmentFacade) ctx.lookup("java:module/CharacterEquipmentFacade");
            characterSaveRecordFacade = (CharacterSaveRecordFacade) ctx.lookup("java:module/CharacterSaveRecordFacade");
            classSaveMasterFacade = (ClassSaveMasterFacade) ctx.lookup("java:module/ClassSaveMasterFacade");
            raceAbilityMasterFacade = (RaceAbilityMasterFacade) ctx.lookup("java:module/RaceAbilityMasterFacade");
            characterSkillRecordFacade = (CharacterSkillRecordFacade) ctx.lookup("java:module/CharacterSkillRecordFacade");
            characterAbilityRecordFacade = (CharacterAbilityRecordFacade) ctx.lookup("java:module/CharacterAbilityRecordFacade");
            saveMasterFacade = (SaveMasterFacade) ctx.lookup("java:module/SaveMasterFacade");
            skillMasterFacade = (SkillMasterFacade) ctx.lookup("java:module/SkillMasterFacade");
            classSkillMasterFacade = (ClassSkillMasterFacade) ctx.lookup("java:module/ClassSkillMasterFacade");
            classMasterFacade = (ClassMasterFacade) ctx.lookup("java:module/ClassMasterFacade");
            characterSkillGrowthRecordFacade = (CharacterSkillGrowthRecordFacade) ctx.lookup("java:module/CharacterSkillGrowthRecordFacade");
            skillMasterFacade = (SkillMasterFacade) ctx.lookup("java:module/SkillMasterFacade");
            religionMasterFacade = (ReligionMasterFacade) ctx.lookup("java:module/ReligionMasterFacade");
            alignmentMasterFacade = (AlignmentMasterFacade) ctx.lookup("java:module/AlignmentMasterFacade");
            abilityMasterFacade = (AbilityMasterFacade) ctx.lookup("java:module/AbilityMasterFacade");
            genderMasterFacade = (GenderMasterFacade) ctx.lookup("java:module/GenderMasterFacade");
            raceMasterFacade = (RaceMasterFacade) ctx.lookup("java:module/RaceMasterFacade");
            characterGrowthRecordFacade = (CharacterGrowthRecordFacade) ctx.lookup("java:module/CharacterGrowthRecordFacade");
            characterRecordFacade = (CharacterRecordFacade) ctx.lookup("java:module/CharacterRecordFacade");
        } catch (NamingException ex) {
            Logger.getLogger(CharacterData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getSkillMiscModifierById(int index) {
        Integer result;
        List<CharacterSkillRecord> skillRecordList = characterRecord.getCharacterSkillRecordList();
        // skillTable の RowIndex と List の index は同一の特技をさしているはず
        result = skillRecordList.get(index).getMiscModifier();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    /*
     * TODO: 経験値が変わったときにこのメソッドを読んで成長記録を追加しなければならないのだが。 どうやって、どういうタイミングで呼ぼうか?
     *
     */
    public void updateGrowthRecord() {

        /*
         * キャラクター成長レコードのリストの作成
         */
        // まずは現在のリストを入手
        List<CharacterGrowthRecord> searchedCharaGrowthList =
                characterGrowthRecordFacade.findByCharacter(characterRecord);
        List<CharacterGrowthRecord> characterGrowthList = new ArrayList<CharacterGrowthRecord>();
        // 足りないレベルのレコードをす。もし無ければ作成。リストは必ずレベルごとにソートされている
        OUTER:
        for (int i = 1; i < getLevel() + 1; i++) {
            for (CharacterGrowthRecord growth : searchedCharaGrowthList) {
                if (growth.getCharacterGrowthRecordPK().getCharacterLevel() == i) {
                    //作成したリストに追加
                    characterGrowthList.add(growth);
                    continue OUTER;
                }
            }
            //無かったようだ。作成する .
            CharacterGrowthRecord newRecord =
                    new CharacterGrowthRecord(characterRecord.getId().intValue(), i);
            try {
                characterGrowthRecordFacade.create(newRecord);
            } catch (Exception e) {
                e.printStackTrace();
                context.addMessage("contents:contentGrid:label1", new FacesMessage(("キャラクターの成長レコードの作成に失敗しました")));
                return;
            }
            // 作成したリストにあらためて追加
            characterGrowthList.add(newRecord);
        }
        //キャラクターレコードにセット
        characterRecord.setCharacterGrowthRecordList(characterGrowthList);

        /*
         * スキル成長レコードのリストの作成
         */
        // まずは現在のリストを入手
        List<CharacterSkillGrowthRecord> searchedCharaSkillGrowthList =
                characterSkillGrowthRecordFacade.findByCharacter(characterRecord);
        List<CharacterSkillGrowthRecord> characterSkillGrowthList = new ArrayList<CharacterSkillGrowthRecord>();
        // 足りないレベルのレコードを探す。もし無ければ作成
        for (int i = 1; i < getLevel() + 1; i++) {
            List<SkillMaster> skilllist = skillMasterFacade.findAll();
            OUTER:
            for (SkillMaster skill : skilllist) {
                for (CharacterSkillGrowthRecord skillgrowth : searchedCharaSkillGrowthList) {
                    if (skillgrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() == i && skillgrowth.getCharacterSkillGrowthRecordPK().getSkillId() == skill.getId().intValue()) {
                        //作成したリストに追加
                        characterSkillGrowthList.add(skillgrowth);
                        continue OUTER;
                    }
                }
                //無かったようだ。作成する .
                CharacterSkillGrowthRecord newRecord =
                        new CharacterSkillGrowthRecord(characterRecord.getId().intValue(), i, skill.getId().intValue());

                //スキルポイントを0に初期化
                newRecord.setSkillPoint(0);
                try {
                    characterSkillGrowthRecordFacade.create(newRecord);
                } catch (Exception e) {
                    e.printStackTrace();
                    context.addMessage("contents:contentGrid:label1", new FacesMessage(("キャラクターの技能成長レコードの作製に失敗しました")));
                    return;
                }

                //作成したリストにあらためて追加
                characterSkillGrowthList.add(newRecord);
            }
        }
        //キャラクターレコードにセット
        characterRecord.setCharacterSkillGrowthRecordList(characterSkillGrowthList);

    }

    /**
     * 合計 HP を計算
     */
    public Integer geHitPoint() {
        int totalHP = 0;
        List<CharacterGrowthRecord> growthList = characterRecord.getCharacterGrowthRecordList();

        int bonus = getAbilityModifierById(CON);
        for (CharacterGrowthRecord growth : growthList) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getLevel()) {
                break;
            }
            if (growth.getHitPoint() != null) {
                totalHP += growth.getHitPoint() + bonus;
            }
        }
        return totalHP;
    }

    public void setSkillMiscModifierById(int index, Integer skillMiscModifier) {
        List<CharacterSkillRecord> skillRecord = characterRecord.getCharacterSkillRecordList();
        // skillTable の RowIndex と List の index は同一の特技をさしているはず
        skillRecord.get(index).setMiscModifier(skillMiscModifier);
    }

    /**
     * 能力値のID(1～6）を指定してから能力値を得る
     */
    public Integer getAbilityBaseById(int id) {
        // 能力値 ID は 1-6 だが、List の ID は 0 から並んでいるので -1 する
        Integer result;
        List<CharacterAbilityRecord> abilityList = characterRecord.getCharacterAbilityRecordList();
        CharacterAbilityRecord ability = abilityList.get(id - 1);
        result = ability.getBase();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    public void setAbilityBaseById(int id, Integer newVal) {
        characterRecord.getCharacterAbilityRecordList().get(id - 1).setBase(newVal);
    }

    /**
     * 能力値のID(1～6）を指定してから能力値を得る
     */
    public Integer getAbilityMiscModifierById(int id) {
        // 能力値 ID は 1-6 だが、List の ID は 0 から並んでいるので -1 する
        Integer result;
        result = characterRecord.getCharacterAbilityRecordList().get(id - 1).getMiscModifier();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    public void setAbilityMiscModifierById(int id, Integer newVal) {
        characterRecord.getCharacterAbilityRecordList().get(id - 1).setMiscModifier(newVal);
        return;
    }

    /**
     * 能力値のID(1～6）を指定してから能力値を得る
     */
    public Integer getAbilityFeatModifierById(int id) {
        // 能力値 ID は 1-6 だが、List の ID は 0 から並んでいるので -1 する
        Integer result;
        result = characterRecord.getCharacterAbilityRecordList().get(id - 1).getFeatModifier();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    public void setAbilityFeatModifierById(int id, Integer newVal) {
        characterRecord.getCharacterAbilityRecordList().get(id - 1).setFeatModifier(newVal);
    }

    /*
     * 能力値 種族 修正値
     */
    public Integer getAbilityRaceModifierById(int id) {
        AbilityMaster ability = abilityMasterFacade.find(id);
        if (ability == null) {
            // abilityTable の RowIndex が不正？
            return 0;
        }
        //まだ種族が選択されてなければ 0 を返す。
        if (characterRecord.getRaceId() == null) {
            return 0;
        }
        RaceMaster race = characterRecord.getRaceId();
        RaceAbilityMaster raceAbility = raceAbilityMasterFacade.findByRaceAndAbility(race, ability);
        if (raceAbility == null) {
            return 0;
        }
        if (raceAbility.getModifier() == null) {
            return 0;
        }
        return raceAbility.getModifier();
    }

    /*
     * 能力値 レベル修正値 (計算値）
     */
    public Integer getAbilityLevelModifierById(int id) {
        int modifier = 0;

        List<CharacterGrowthRecord> growthList =
                characterGrowthRecordFacade.findByCharacter(characterRecord);

        for (CharacterGrowthRecord growth : growthList) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel()) {
                break;
            }
            Integer enhancedAb = growth.getAbilityEnhancement();
            //セットされていないレベルは飛ばす
            if (enhancedAb == null) {
                continue;
            }
            if (id == enhancedAb.intValue()) {
                modifier++;
            }
        }
        return modifier;
    }
    /*
     * 能力値 合計
     */

    public Integer getAbilityTotalById(int id) {
        return getAbilityBaseById(id)
                + getAbilityRaceModifierById(id)
                + getAbilityFeatModifierById(id)
                + getAbilityMiscModifierById(id)
                + getAbilityLevelModifierById(id);
    }
    /*
     * 能力値 修正値
     */

    public Integer getAbilityModifierById(int ability) {

        return (getAbilityTotalById(ability) / 2) - 5;
    }
    /*
     * 技能 対応能力修正値
     */

    public Integer getSkillAbilityModifierById(int skill) {

        return getAbilityModifierById(skillMasterFacade.find(skill).getAbilityId().getId());
    }
    /*
     * 技能 対応能力値名
     */

    public String getSkillAbilityNameById(int skill) {

        return skillMasterFacade.find(skill).getAbilityId().getAbilityName();
    }
    /*
     * 技能 対応能力値名 省略名
     */

    public String getSkillAbilityShortNameById(int skill) {

        String name;
        name = skillMasterFacade.find(skill).getAbilityId().getAbilityName();
        return name.substring(0, 1);
    }
    /*
     * 技能 ポイント
     */

    public Integer getSkillTotalPointById(int skill) {
        int point = 0;

        List<CharacterSkillGrowthRecord> skillGrowthlist = characterRecord.getCharacterSkillGrowthRecordList();
        for (CharacterSkillGrowthRecord skillGrowth : skillGrowthlist) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() > getCharacterLevel()) {
                break;
            }
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getSkillId() == skill) {
                point += skillGrowth.getSkillPoint().intValue();
            }
        }
        return point;
    }
    /*
     * 技能 判定値
     */

    public Integer getSkillTotalCheckModifierById(Integer index) {
        return getSkillAbilityModifierById(index)
                + getSkillTotalRankById(index)
                + getSkillMiscModifierById(index)
                + getSkillArmorModifierById(index)
                + getSkillSynergyModifierById(index);
    }

    /*
     * 技能 ランク（計算値) ポイントとクラスから計算する
     */
    protected Integer skillRank;
    protected Integer skillRankByLevelAndSkill;

    /**
     * 技能ランク。 <p>クラス外技能の場合 1/2 になるの float を返す。受け取り側で切り捨てする必要がある</p>
     *
     * @param growth キャラクタ成長レコード
     * @param skill スキルマスター
     * @return スキルランク
     */
    public Float getSkillRankByLevelAndSkill(CharacterGrowthRecord growth, SkillMaster skill) {
        int point = 0;
        float rank;
        ClassMaster klass = growth.getClassId();

        //まずは技能成長レコードからこのレベルでのスキルポイントを得る
        List<CharacterSkillGrowthRecord> skillgrowthlist = characterRecord.getCharacterSkillGrowthRecordList();
        for (CharacterSkillGrowthRecord skillGrowth : skillgrowthlist) {
            Integer level = skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel();
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (level > getCharacterLevel()) {
                break;
            }
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getSkillId() == skill.getId().intValue()
                    && level == growth.getCharacterGrowthRecordPK().getCharacterLevel()) {
                point = skillGrowth.getSkillPoint().intValue();
                break;
            }
        }
        if (point == 0) {
            //ポイントが0ならクラス技能かどうか確認する必要はない
            return 0f;
        }
        // つづいてクラス技能かどうか確認する
        if (isClassSkillByLevelAndSkill(growth, skill)) {
            //クラス技能
            rank = point;
        } else {
            //クラス外技能
            rank = (new Float(point)) / 2f;
        }
        return rank;
    }

    public Integer getSkillTotalRankById(int skillId) {
        Float totalRank = 0f;

        SkillMaster skill = skillMasterFacade.find(skillId);
        // レベル毎のループ
        List<CharacterGrowthRecord> growthlist = characterRecord.getCharacterGrowthRecordList();
        for (CharacterGrowthRecord growth : growthlist) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel()) {
                break;
            }
            totalRank += getSkillRankByLevelAndSkill(growth, skill);
        }
        return (totalRank.intValue());
    }

    /*
     * 技能がランク無しでも実施可能か
     */
    protected boolean skillAcceptNoRankBySkillId;

    public boolean isSkillAcceptoRankBySkillId(int skillid) {
        SkillMaster skill = skillMasterFacade.find(skillid);

        if (skill == null) {
            return false;
        }

        return (skill.getAcceptNoRank().intValue() == 1);
    }

    /**
     * クラス技能かどうか
     */
    public boolean isClassSkillByClassAndSkill(ClassMaster klass, SkillMaster skill) {
        List<ClassSkillMaster> classSkillList = classSkillMasterFacade.findByClass(klass);
        for (ClassSkillMaster classSkill : classSkillList) {
            if (classSkill.getClassSkillMasterPK().getSkillId() == skill.getId().intValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * あるスキルがこのキャラにとってクラス技能かどうかをチェックする。 習得しているクラスの中でひとつでも該当技能がクラス技能であればクラス技能になる
     *
     * @param skill
     * @return boolean
     */
    public boolean isClassSkillByLevelAndSkill(CharacterGrowthRecord growthLevel, SkillMaster skill) {
        List<CharacterGrowthRecord> growthList = characterRecord.getCharacterGrowthRecordList();
        for (CharacterGrowthRecord growth : growthList) {
            // チェックしようとしているレベル以下のクラスしか確認しない
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > growthLevel.getCharacterGrowthRecordPK().getCharacterLevel()) {
                break;
            }
            ClassMaster klass = growth.getClassId();
            if (klass == null) {
                continue;
            }
            List<ClassSkillMaster> classSkillList = classSkillMasterFacade.findByClass(klass);
            for (ClassSkillMaster classSkill : classSkillList) {
                if (classSkill.getClassSkillMasterPK().getSkillId() == skill.getId().intValue()) {
                    return true;
                }
            }
        }
        return false;
    }
    /*
     * 技能ランクを持っているかどうか
     */

    public boolean hasSkillRankBySkill(SkillMaster skill) {
        List<CharacterSkillGrowthRecord> skillGrowthList = characterRecord.getCharacterSkillGrowthRecordList();

        for (CharacterSkillGrowthRecord skillGrowth : skillGrowthList) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() > getCharacterLevel()) {
                break;
            }
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getSkillId() == skill.getId().intValue()
                    && skillGrowth.getSkillPoint() != null
                    && skillGrowth.getSkillPoint().intValue() > 0) {
                return true;
            }
        }
        return false;
    }

    /*
     * 技能 鎧、盾ペナルティ
     */
    public Integer getSkillArmorModifierById(int skillId) {
        int result = 0;
        SkillMaster skill = skillMasterFacade.find(skillId);

        if (skill.getArmorCheck() == 0) {
            //この技能に防具ペナルティはない
            return 0;
        }

        CharacterEquipment equip = characterRecord.getCharacterEquipment();
        if (equip.getSkillArmorMod() != null) {
            result += equip.getSkillArmorMod();
        }
        if (equip.getSkillShieldMod() != null) {
            result += equip.getSkillShieldMod();
        }
        if (skillId == 22) {
            // 水泳は2倍！！
            result *= 2;
        }
        return result;
    }

    /**
     * 技能 相乗効果
     */
    public Integer getSkillSynergyModifierById(int skillId) {
        int result = 0;
        SkillMaster skill = skillMasterFacade.find(skillId);

        List<SkillSynergyMaster> synergyList = skillSynergyMasterFacade.findBySkill(skill);
        for (SkillSynergyMaster synergy : synergyList) {
            int affectedSkillId = synergy.getSkillSynergyMasterPK().getAffectedBy();
            int affectedSkillRank = getSkillTotalRankById(affectedSkillId);
            if (affectedSkillRank > 0) {
                //ランク 5 で対象技能が + 2
                result += (affectedSkillRank / 5) * 2;
            }
        }
        return result;
    }

    public Integer getSaveAbilityModifierById(int saveId) {

        return getAbilityModifierById(saveMasterFacade.find(saveId).getAbilityId().getId());
    }
    /*
     * セーヴボーナス クラス合計
     */

    public Integer getSaveClassBonusById(int saveId) {
        int bonus = 0;

        SaveMaster save = saveMasterFacade.find(saveId);

        List<CharacterGrowthRecord> growthList = characterRecord.getCharacterGrowthRecordList();

        //習得しているクラスごとに最大レベルを求める
        Map<Integer, Integer> classMap = new HashMap<Integer, Integer>();
        for (CharacterGrowthRecord growth : growthList) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel()) {
                break;
            }
            if (growth.getClassId() == null) {
                //まだクラスが設定されていないもよう
                continue;
            }
            Integer classId = growth.getClassId().getId();
            if (classMap.get(classId) == null) {
                classMap.put(classId, 1);
                continue;
            } else {
                classMap.put(classId, classMap.get(classId) + 1);
            }
        }

        //クラスごとに最大レベルからクラスボーナスを得る
        for (Map.Entry<Integer, Integer> classEntry : classMap.entrySet()) {
            Integer classId = classEntry.getKey();
            Integer lv = classEntry.getValue();

            BonusRankMaster rank = classSaveMasterFacade.findByClassAndSave(classMasterFacade.find(classId), save).getRankId();
            if (rank == null) {
                //まだクラスのセーブボーナスランクが設定されていないもよう
                return 0;
            }
            switch (rank.getId().intValue()) {
                case 1:
                    bonus += lv / 2 + 2;
                    break;
                case 2:
                    bonus += lv;
                    break;
                case 3:
                    bonus += lv / 3;
                    break;
                default:
                    bonus = 0;
            }
        }
        return bonus;
    }
    /*
     * セーヴ その他修正値
     */

    public Integer getSaveMiscModifierById(int saveId) {
        Integer result;
        CharacterSaveRecord saveRecord = characterRecord.getCharacterSaveRecordList().get(saveId - 1);
        result = saveRecord.getMiscModifier();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    /*
     * セーヴ 種族修正値
     */
    public Integer getSaveRaceModifierById(int saveId) {
        if (characterRecord.getRaceId() != null) {
            List<RaceSaveMaster> raceSaveList = (List<RaceSaveMaster>) characterRecord.getRaceId().getRaceSaveMasterCollection();
            for (RaceSaveMaster raceSave : raceSaveList) {
                if (raceSave.getRaceSaveMasterPK().getSaveId() == saveId) {
                    if (raceSave.getModifier() != null) {
                        return raceSave.getModifier();
                    }
                }
            }
        }
        return 0;
    }

    /*
     * セーヴボーナス トータル計
     */
    public Integer getSaveTotalById(int saveId) {
        return getSaveAbilityModifierById(saveId)
                + getSaveClassBonusById(saveId)
                + getSaveMiscModifierById(saveId)
                + getSaveRaceModifierById(saveId);
    }
    /*
     * イニシアチブ その修正
     */
    protected Integer initiativeMiscModifier;

    public Integer getInitiativeMiscModifier() {
        Integer mod = characterRecord.getInitiativeMiscModifier();
        if (mod == null) {
            return new Integer(0);
        }
        return mod;
    }

    public void setInitiativeMiscModifier(Integer modifier) {
        characterRecord.setInitiativeMiscModifier(modifier);
    }
    /*
     * イニシアチブ 技能修正
     */
    protected Integer initiativeFeatModifier;

    public Integer getInitiativeFeatModifier() {
        Integer mod = characterRecord.getInitiativeFeatModifier();
        if (mod == null) {
            return new Integer(0);
        }
        return mod;
    }

    public void setInitiativeFeatModifier(Integer modifier) {
        characterRecord.setInitiativeFeatModifier(modifier);
    }

    /*
     * イニシアチブ 能力値修正
     */
    public Integer getInitiativeAbilityModifier() {
        // 敏捷力の ID は 2
        return getAbilityModifierById(DEX);
    }
    /*
     * イニシアチブ 合計 （計算値)
     */
    protected Integer initiativeTotal;

    public Integer getInitiativeTotal() {
        return getInitiativeAbilityModifier() + getInitiativeFeatModifier() + getInitiativeMiscModifier();
    }
    /*
     * 移動速度 合計 （計算値)
     */
    protected Integer speed;

    public Integer getSpeedTotal() {
        return getSpeedRaceBasse() + getSpeedFeatModifier() + getSpeedMiscModifier();
    }

    @Override
    public String getSpeed() {
        StringBuilder str = new StringBuilder();
        str.append(getSpeedTotal());
        str.append("フィート(");
        str.append((int) getSpeedTotal() / 5);
        str.append("マス)");
        return str.toString();
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    /*
     * 移動速度 種族基本速度
     */
    protected Integer speedRaceBasse;

    public Integer getSpeedRaceBasse() {
        RaceMaster race = characterRecord.getRaceId();
        if (race == null) {
            //まだ種族が決まってない。 デフォルト
            return new Integer(0);
        }
        return race.getSpeed();
    }
    /*
     * 移動速度 特技修正値
     */
    protected Integer speedFeatModifier;

    public Integer getSpeedFeatModifier() {
        Integer mod = characterRecord.getSpeedFeatModifier();
        if (mod == null) {
            return new Integer(0);
        }
        return mod;
    }

    public void setSpeedFeatModifier(Integer speedFeatModifier) {
        characterRecord.setSpeedFeatModifier(speedFeatModifier);
    }
    /*
     * 移動 その他修正値
     */
    protected Integer speedMiscModifier;

    public Integer getSpeedMiscModifier() {
        Integer mod = characterRecord.getSpeedMiscModifier();
        if (mod == null) {
            return new Integer(0);
        }
        return mod;
    }

    public void setSpeedMiscModifier(Integer speedMiscModifier) {
        characterRecord.setSpeedMiscModifier(speedMiscModifier);
    }

    public void textField13_processValueChange(ValueChangeEvent vce) {
    }

    /**
     * AC アーマークラス(計算値)
     */
    public Integer getAcNormal() {
        CharacterRecord chara = characterRecord;
        return 10
                + getAcAbilityModifier()
                + (getAcRaceModifier() == null ? 0 : getAcRaceModifier())
                + (chara.getAcArmor() == null ? 0 : chara.getAcArmor())
                + (chara.getAcShield() == null ? 0 : chara.getAcShield())
                + (chara.getAcMiscMod() == null ? 0 : chara.getAcMiscMod());
    }

    /**
     * 接触AC<p> 種族ボーナスとその他ボーナス。本当はボーナスの種類で決めるべきか。<p> 例えば､反発ボーナスは組み込めるが盾ボーナスはだめとか。
     *
     * @return
     */
    public Integer getAcTouch() {
        CharacterRecord chara = characterRecord;
        return 10
                + getAcAbilityModifier()
                + (getAcRaceModifier() == null ? 0 : getAcRaceModifier())
                + (chara.getAcMiscMod() == null ? 0 : chara.getAcMiscMod());
    }

    public Integer getAcFlatFooted() {
        CharacterRecord chara = characterRecord;
        return 10
                + (getAcRaceModifier() == null ? 0 : getAcRaceModifier())
                + (chara.getAcArmor() == null ? 0 : chara.getAcArmor())
                + (chara.getAcShield() == null ? 0 : chara.getAcShield())
                + (chara.getAcMiscMod() == null ? 0 : chara.getAcMiscMod());
    }

    /**
     * AC 能力値修正
     */
    public Integer getAcAbilityModifier() {
        // 敏捷力の ID は 2. 防具によって、敏捷力ボーナスがどれだけ適用できるかがきまる。
        int bonus;
        CharacterEquipment equip = characterRecord.getCharacterEquipment();
        bonus = getAbilityModifierById(DEX).intValue();
        if (equip.getDexAcArmorLimit() != null && equip.getDexAcArmorLimit() < bonus) {
            bonus = equip.getDexAcArmorLimit().intValue();
        }
        if (equip.getDexAcShieldLimit() != null && equip.getDexAcShieldLimit() < bonus) {
            bonus = equip.getDexAcShieldLimit().intValue();
        }
        return bonus;
    }
    /*
     * AC 盾ボーナス
     */
    protected Integer acShield;

    /**
     * Get the value of acShield
     *
     * @return the value of acShield
     */
    public Integer getAcShield() {
        return characterRecord.getAcShield();
    }

    /*
     * AC 鎧ボーナス
     */
    protected Integer acArmor;

    /**
     * Get the value of acArmor
     *
     * @return the value of acArmor
     */
    public Integer getAcArmor() {
        return characterRecord.getAcArmor();
    }
    /*
     * AC 種族修正
     */
    protected Integer acRace;

    public Integer getAcRaceModifier() {
        RaceMaster race = characterRecord.getRaceId();
        // 種族未設定
        if (race == null) {
            return 0;
        }

        return race.getSizeId().getAcModifier();
    }
    /**
     * AC その他
     */
    protected Integer acMiscMod;

    public Integer getAcMiscMod() {
        return characterRecord.getAcMiscMod();
    }

    /**
     * ヒットポイント用能力値修正
     */
    public Integer getHitPointAbilityModifier() {
        // 敏捷力の ID は 2
        return getAbilityModifierById(CON);
    }

    /**
     * 攻撃ボーナス 基本攻撃ボーナス
     */
    public Integer getBaseAttackTotal() {
        int baseAttack = 0;
        //クラスのリストをつくり、各クラスのレベルを計算する
        List<CharacterGrowthRecord> growthList = characterRecord.getCharacterGrowthRecordList();
        Map<ClassMaster, Integer> classMap = new HashMap<ClassMaster, Integer>();
        for (CharacterGrowthRecord growth : growthList) {
            // 現在のキャラクタレベル分だけしか見ないでよい。
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel()) {
                break;
            }
            int newVal;
            ClassMaster klass = growth.getClassId();

            if (classMap.get(klass) == null) {
                newVal = 1;
            } else {
                newVal = classMap.get(klass).intValue() + 1;
            }
            classMap.put(klass, newVal);
        }
        for (Map.Entry<ClassMaster, Integer> mapEntry : classMap.entrySet()) {
            ClassMaster klass = mapEntry.getKey();
            baseAttack += getBaseAttackByClassAndLevel(klass, mapEntry.getValue());
        }
        return baseAttack;
    }

    public Integer getBaseAttackByClassAndLevel(ClassMaster klass, Integer lv) {
        if (klass == null) {
            return 0;
        }
        Integer rank = klass.getBaseAttackRankId().getId();
        if (rank == null) {
            //クラスにBABが未設定の時は劣悪と判断
            return (1 / 2) * lv;
        }
        Float result; // 端数の計算のため
        switch (rank) {
            case 1: // 良好
                return lv;
            case 2: //平均
                result = lv * (3F / 4F);
                return result.intValue();
            case 3: // 劣悪
                result = lv * (1F / 2F);
                return result.intValue();
            default: // 無し？
                return 0;
        }
    }

    /**
     * 攻撃ボーナス 遠隔攻撃ボーナス
     */
    public Integer getRangeAttackBonus() {
        return getBaseAttackTotal() + getAbilityModifierById(DEX);
    }

    /**
     * 攻撃ボーナス 近接攻撃ボーナス
     */
    public Integer getMeleeAttackBonus() {
        return getBaseAttackTotal() + getAbilityModifierById(STR);
    }
    /*
     * 攻撃ボーナス 組み付きボーナス
     */

    public Integer getGrappleBonus() {
        return getMeleeAttackBonus();
    }
    /*
     * 攻撃ボーナス 近接筋力ボーナス
     */

    public Integer getAttackBonusStrengthBonus() {
        return getAbilityModifierById(STR);
    }
    /*
     * 攻撃ボーナス 遠隔筋力ボーナス
     */

    public Integer getAttackBonusDexBonus() {
        return getAbilityModifierById(DEX);
    }
    /**
     * アイテム 武器 TODO: 未実装
     */
    /*
     * public String getArm1 (){ ArmMaster arm1 =
     * characterRecord.getCharacterEquipment().getArm1(); if(arm1 != null) {
     * return arm1.getName(); } return "未装備"; } public String getArm2 (){
     * ArmMaster arm2 = characterRecord.getCharacterEquipment().getArm2();
     * if(arm2 != null) { return arm2.getName(); } return "未装備"; }
     */
    public static final int STR = 1;
    public static final int DEX = 2;
    public static final int CON = 3;
    public static final int INT = 4;
    public static final int WIS = 5;
    public static final int CHA = 6;
    public static final int FORTITUTE = 1;
    public static final int REFLEX = 2;
    public static final int WILL = 3;

    public Integer getLevel() {
        Integer exp = characterRecord.getExperience().intValue();
        int lv = 0;
        int val = 0;
        do {
            lv++;
            val += lv * 1000;
        } while (exp.intValue() >= val);
        return (new Integer(lv));
    }

    public Integer getExpForNextLevel() {
        int lv = 0;
        int val = 0;
        do {
            lv++;
            val += lv * 1000;
        } while (characterRecord.getExperience().intValue() >= val);
        return (new Integer(val));
    }

    /**
     * 最終更新日
     */
    public String getLastChange() {
        Date date = characterRecord.getSaveTime();

        if (date == null) {
            return "--未保存--";
        }
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
        return fmt.format(date);
    }

    /**
     * 改行を<br>に変換
     */
    public static String newLineToBr(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        StringCharacterIterator sci = new StringCharacterIterator(str);
        for (char c = sci.current(); c != StringCharacterIterator.DONE; c = sci.next()) {
            if (c == '\n') {
                sb.append("<br>");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public Integer getCharacterLevel() {
        int lv = 0;
        int val = 0;
        do {
            lv++;
            val += lv * 1000;
        } while (characterRecord.getExperience().intValue() >= val);
        return new Integer(lv);
    }

    public String getClassList() {
        List growthList = characterRecord.getCharacterGrowthRecordList();
        Map classMap = new HashMap();
        int i = 1;
        Iterator it = growthList.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterGrowthRecord growth = (CharacterGrowthRecord) it.next();
            if (i > getCharacterLevel().intValue()) {
                break;
            }
            int newVal;
            ClassMaster klass = growth.getClassId();
            if (classMap.get(klass) == null) {
                newVal = 1;
            } else {
                newVal = ((Integer) classMap.get(klass)).intValue() + 1;
            }
            classMap.put(klass, Integer.valueOf(newVal));
            i++;
        } while (true);
        String classList = "";
        for (Iterator iter = classMap.entrySet().iterator(); iter.hasNext();) {
            java.util.Map.Entry mapEntry = (java.util.Map.Entry) iter.next();
            ClassMaster klass = (ClassMaster) mapEntry.getKey();
            String line = (new StringBuilder()).append(klass != null ? klass.getClassName() : "\u672A\u8A2D\u5B9A").append(mapEntry.getValue()).append(" Lv").append(", ").toString();
            classList = (new StringBuilder()).append(classList).append(line).toString();
        }

        return classList;
    }

    public String getCampaignName() {
        return characterRecord.getCampaignId() != null ? characterRecord.getCampaignId().getCampaignName() : "\u672A\u8A2D\u5B9A";
    }

    @Override
    public Integer getInitiative() {
        return Integer.valueOf(getInitiativeAbilityModifier().intValue() + characterRecord.getInitiativeFeatModifier().intValue()
                + characterRecord.getInitiativeMiscModifier().intValue());
    }

    public SaveMaster getSaveMasterById(int saveId) {
        SaveMaster save = null;
        Iterator it = characterRecord.getCharacterSaveRecordList().iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterSaveRecord saveRecord = (CharacterSaveRecord) it.next();
            if (saveRecord.getSaveMaster().getId().intValue() == saveId) {
                save = saveRecord.getSaveMaster();
            }
        } while (true);
        return save;
    }

    @Override
    public Integer getHitPoint() {
        List growthList = characterRecord.getCharacterGrowthRecordList();
        int total = 0;
        int bonus = getAbilityModifierById(3).intValue();
        Iterator it = growthList.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterGrowthRecord growth = (CharacterGrowthRecord) it.next();
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel().intValue()) {
                break;
            }
            if (growth.getHitPoint() != null) {
                total += growth.getHitPoint().intValue() + bonus;
            }
        } while (true);
        return Integer.valueOf(total);
    }

    public SkillMaster getSkillMasterById(int id) {
        List charaSkillGrowthList = characterRecord.getCharacterSkillGrowthRecordList();
        for (Iterator it = charaSkillGrowthList.iterator(); it.hasNext();) {
            CharacterSkillGrowthRecord skillGrowthRecord = (CharacterSkillGrowthRecord) it.next();
            SkillMaster skill = skillGrowthRecord.getSkillMaster();
            if (skill.getId() != null && skill.getId().intValue() == id) {
                return skillGrowthRecord.getSkillMaster();
            }
        }

        return null;
    }

    public String getskillAbilityNameById(int id) {
        SkillMaster skill = getSkillMasterById(id);
        return skill.getAbilityId().getAbilityName();
    }

    public String getskillAbilityShortNameById(int id) {
        SkillMaster skill = getSkillMasterById(id);
        String name = skill.getAbilityId().getAbilityName();
        return name.substring(0, 1);
    }

    public Integer getSkillTotalCheckModifierById(int id) {
        return Integer.valueOf(getSkillAbilityModifierById(id).intValue() + getSkillTotalRankById(id).intValue() + getskillMiscModifierById(id).intValue() + getskillArmorModifierById(id).intValue() + getskillSynergyModifierById(id).intValue());
    }

    public Integer getskillMiscModifierById(int id) {
        List skillRecordList = characterRecord.getCharacterSkillRecordList();
        Integer result = ((CharacterSkillRecord) skillRecordList.get(id - 1)).getMiscModifier();
        if (result == null) {
            return Integer.valueOf(0);
        } else {
            return result;
        }
    }

    public Float getskillRankByLevelAndSkill(CharacterGrowthRecord growth, SkillMaster skill) {
        int point = 0;
        float rank;
        ClassMaster klass = growth.getClassId();
        List skillgrowthlist = characterRecord.getCharacterSkillGrowthRecordList();
        Iterator it = skillgrowthlist.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterSkillGrowthRecord skillGrowth = (CharacterSkillGrowthRecord) it.next();
            Integer level = Integer.valueOf(skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel());
            if (level.intValue() > getCharacterLevel().intValue()) {
                break;
            }
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getSkillId() != skill.getId().intValue() || level.intValue() != growth.getCharacterGrowthRecordPK().getCharacterLevel()) {
                continue;
            }
            point = skillGrowth.getSkillPoint().intValue();
            break;
        } while (true);
        if (point == 0) {
            return Float.valueOf(0.0F);
        }
        if (isClassSkillByLevelAndSkill(growth, skill)) {
            rank = point;
        } else {
            rank = (new Float(point)).floatValue() / 2.0F;
        }
        return Float.valueOf(rank);
    }

    public Integer getskillArmorModifierById(int skillId) {
        SkillMaster skill = getSkillMasterById(skillId);
        int result = 0;
        if (skill.getArmorCheck() == 0) {
            return Integer.valueOf(0);
        }
        CharacterEquipment equip = characterRecord.getCharacterEquipment();
        if (equip.getSkillArmorMod() != null) {
            result += equip.getSkillArmorMod().intValue();
        }
        if (equip.getSkillShieldMod() != null) {
            result += equip.getSkillShieldMod().intValue();
        }
        if (skillId == 22) {
            result *= 2;
        }
        return Integer.valueOf(result);
    }

    public Integer getskillSynergyModifierById(int skillId) {
        SkillMaster skill = getSkillMasterById(skillId);
        int result = 0;
        List synergyList = skill.getSkillSynergyMasterList();
        Iterator it = synergyList.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            SkillSynergyMaster synergy = (SkillSynergyMaster) it.next();
            int affectedSkillId = synergy.getSkillSynergyMasterPK().getAffectedBy();
            int affectedSkillRank = getSkillTotalRankById(affectedSkillId).intValue();
            if (affectedSkillRank > 0) {
                result += (affectedSkillRank / 5) * 2;
            }
        } while (true);
        return Integer.valueOf(result);
    }
    private String attackDescriptionWithBR;

    public String getAttackDescriptionWithBR() {
        return newLineToBr(characterRecord.getAttackDescription());
    }

    public void setAttackDescriptionWithBR(String attackDescriptionWithBR) {
        this.attackDescriptionWithBR = attackDescriptionWithBR;
    }

    public static String getAbilityShortName(AbilityMaster ability) {
        String name = ability.getAbilityName();
        return (new StringBuilder()).append("\u3010").append(name.substring(0, 1)).append("\u3011").toString();
    }

    public AlignmentMaster getAlignmentId() {
        return characterRecord.getAlignmentId();
    }

    public CampaignMaster getCampaignId() {
        return characterRecord.getCampaignId();
    }

    public CharacterEquipment getCharacterEquipment() {
        return characterRecord.getCharacterEquipment();
    }

    public GenderMaster getGenderId() {
        return characterRecord.getGenderId();
    }

    public Integer getAge() {
        return characterRecord.getAge();
    }

    public Integer getDamageReduction() {
        return characterRecord.getDamageReduction();
    }

    public Integer getExperience() {
        return characterRecord.getExperience();
    }

    public Integer getHeight() {
        return characterRecord.getHeight();
    }

    public Integer getId() {
        return characterRecord.getId();
    }

    public Integer getSheetId() {
        return characterRecord.getSheetId();
    }

    public Integer getSpellResistance() {
        return characterRecord.getSpellResistance();
    }

    public Integer getWeight() {
        return characterRecord.getWeight();
    }

    public List<CharacterGrowthRecord> getCharacterGrowthRecordList() {
        return characterRecord.getCharacterGrowthRecordList();
    }

    public RaceMaster getRaceId() {
        return characterRecord.getRaceId();
    }

    public ReligionMaster getReligionId() {
        return characterRecord.getReligionId();
    }

    public String getAttackDescription() {
        return characterRecord.getAttackDescription();
    }

    public String getCharacterName() {
        return characterRecord.getCharacterName();
    }

    public String getDefenceDescription() {
        return characterRecord.getDefenceDescription();
    }

    public String getDescription() {
        return characterRecord.getDescription();
    }

    public String getEyeColor() {
        return characterRecord.getEyeColor();
    }

    public String getFeatDescription() {
        return characterRecord.getFeatDescription();
    }

    public String getHairColor() {
        return characterRecord.getHairColor();
    }

    public String getItemDescription() {
        return characterRecord.getItemDescription();
    }

    public String getLanguage() {
        return characterRecord.getLanguage();
    }

    public String getPlayerName() {
        return characterRecord.getPlayerName();
    }

    public String getSkinColor() {
        return characterRecord.getSkinColor();
    }

    public String getSpellDescription() {
        return characterRecord.getSpellDescription();
    }

    public String getUpdateDescription() {
        return characterRecord.getUpdateDescription();
    }

    public void setAcArmor(Integer acArmor) {
        characterRecord.setAcArmor(acArmor);
    }

    public void setAcMiscMod(Integer acMiscMod) {
        characterRecord.setAcMiscMod(acMiscMod);
    }

    public void setAcShield(Integer acShield) {
        characterRecord.setAcShield(acShield);
    }

    public void setAge(Integer age) {
        characterRecord.setAge(age);
    }

    public void setAlignmentId(AlignmentMaster alignmentId) {
        characterRecord.setAlignmentId(alignmentId);
    }

    public void setAttackDescription(String attackDescription) {
        characterRecord.setAttackDescription(attackDescription);
    }

    public void setCampaignId(CampaignMaster campaignId) {
        characterRecord.setCampaignId(campaignId);
    }

    public void setCharacterAbilityRecordList(List<CharacterAbilityRecord> characterAbilityRecordCollection) {
        characterRecord.setCharacterAbilityRecordList(characterAbilityRecordCollection);
    }

    public void setCharacterEquipment(CharacterEquipment characterEquipment) {
        characterRecord.setCharacterEquipment(characterEquipment);
    }

    public void setCharacterGrowthRecordList(List<CharacterGrowthRecord> characterGrowthRecordList) {
        characterRecord.setCharacterGrowthRecordList(characterGrowthRecordList);
    }

    public void setCharacterName(String characterName) {
        characterRecord.setCharacterName(characterName);
    }

    public void setCharacterSaveRecordList(List<CharacterSaveRecord> characterSaveRecordList) {
        characterRecord.setCharacterSaveRecordList(characterSaveRecordList);
    }

    public void setCharacterSkillGrowthRecordList(List<CharacterSkillGrowthRecord> characterSkillGrowthRecordList) {
        characterRecord.setCharacterSkillGrowthRecordList(characterSkillGrowthRecordList);
    }

    public List<CharacterSkillGrowthRecord> getCharacterSkillGrowthRecordList() {
        return characterRecord.getCharacterSkillGrowthRecordList();
    }

    public void setCharacterSkillRecordList(List<CharacterSkillRecord> characterSkillRecordList) {
        characterRecord.setCharacterSkillRecordList(characterSkillRecordList);
    }

    public void setDamageReduction(Integer damageReduction) {
        characterRecord.setDamageReduction(damageReduction);
    }

    public void setDefenceDescription(String defenceDescription) {
        characterRecord.setDefenceDescription(defenceDescription);
    }

    public void setDescription(String description) {
        characterRecord.setDescription(description);
    }

    public void setExperience(Integer experience) {
        characterRecord.setExperience(experience);
    }

    public void setEyeColor(String eyeColor) {
        characterRecord.setEyeColor(eyeColor);
    }

    public void setFeatDescription(String featDescription) {
        characterRecord.setFeatDescription(featDescription);
    }

    public void setGenderId(GenderMaster genderId) {
        characterRecord.setGenderId(genderId);
    }

    public void setHairColor(String hairColor) {
        characterRecord.setHairColor(hairColor);
    }

    public void setHeight(Integer height) {
        characterRecord.setHeight(height);
    }

    public void setId(Integer id) {
        characterRecord.setId(id);
    }

    public void setItemDescription(String itemDescription) {
        characterRecord.setItemDescription(itemDescription);
    }

    public void setLanguage(String language) {
        characterRecord.setLanguage(language);
    }

    public void setPlayerName(String playerName) {
        characterRecord.setPlayerName(playerName);
    }

    public void setRaceId(RaceMaster raceId) {
        characterRecord.setRaceId(raceId);
    }

    public void setReligionId(ReligionMaster religionId) {
        characterRecord.setReligionId(religionId);
    }

    public void setSaveTime(Date saveTime) {
        characterRecord.setSaveTime(saveTime);
    }

    public void setSheetId(Integer sheetId) {
        characterRecord.setSheetId(sheetId);
    }

    public void setSkinColor(String skinColor) {
        characterRecord.setSkinColor(skinColor);
    }

    public void setSpellDescription(String spellDescription) {
        characterRecord.setSpellDescription(spellDescription);
    }

    public void setSpellResistance(Integer spellResistance) {
        characterRecord.setSpellResistance(spellResistance);
    }

    public void setUpdateDescription(String updateDescription) {
        characterRecord.setUpdateDescription(updateDescription);
    }

    public void setWeight(Integer weight) {
        characterRecord.setWeight(weight);
    }

    public List<CharacterSaveRecord> getCharacterSaveRecordList() {
        return characterRecord.getCharacterSaveRecordList();
    }

    public void save() {
        CharacterEquipment equip = characterRecord.getCharacterEquipment();
        List<CharacterSkillRecord> skillRecordList = characterRecord.getCharacterSkillRecordList();
        List<CharacterSkillGrowthRecord> skillGrowthList = characterRecord.getCharacterSkillGrowthRecordList();
        List<CharacterGrowthRecord> growthList = characterRecord.getCharacterGrowthRecordList();
        List<CharacterAbilityRecord> abilityList = characterRecord.getCharacterAbilityRecordList();
        List<CharacterSaveRecord> saveList = characterRecord.getCharacterSaveRecordList();

        //更新時間を記録
        Date date = new Date();
        characterRecord.setSaveTime(date);

        //更新
        characterRecordFacade.edit(characterRecord);
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
    }

    public void remove() {
        characterRecordFacade.remove(characterRecord);
    }

    @Override
    public String getAbilities() {
        StringBuilder str = new StringBuilder();
        List<CharacterAbilityRecord> abilityList = characterRecord.getCharacterAbilityRecordList();
        for (CharacterAbilityRecord ability : abilityList) {
            str.append(getAbilityShortName(ability.getAbilityMaster())
                    + " " + getAbilityTotalById(ability.getAbilityMaster().getId())
                    + "(" + getAbilityModifierById(ability.getAbilityMaster().getId()) + ")<br>");
        }
        return str.toString();
    }

    @Override
    public String getArmorClass() {
        return (getAcNormal() + "/" + getAcTouch() + "/" + getAcFlatFooted());
    }

    @Override
    public Integer getChallengeRating() {
        return getLevel();
    }

    @Override
    public String getEnvironment() {
        return "";
    }

    @Override
    public String getFeats() {
        return "";
    }

    @Override
    public String getAttack() {
        return getAttackDescriptionWithBR();
    }

    @Override
    public String getFullAttack() {
        return "";
    }

    @Override
    public String getHitDice() {
        StringBuilder str = new StringBuilder();
        List<CharacterGrowthRecord> growthList = characterRecord.getCharacterGrowthRecordList();


        int bonus = getAbilityModifierById(CON);
        for (CharacterGrowthRecord growth : growthList) {
            // 経験値から見たキャラクタレベルだけで判断する。
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() == getLevel()) {
                if (growth.getClassId() == null) {
                    return ("未設定");
                }
                str.append(getLevel());
                str.append(growth.getClassId().getHitDiceType().getName());
            }
        }
        str.append("(");
        str.append(getHitPoint());
        str.append("hp)");
        return str.toString();
    }

    @Override
    public String getKlass() {
        return getClassList();
    }

    @Override
    public String getName() {
        return getCharacterName();
    }

    @Override
    public String getOrganization() {
        return "";
    }

    @Override
    public String getSave() {
        return (getSaveTotalById(1) + "/" + getSaveTotalById(2) + "/" + getSaveTotalById(3));
    }

    private String getSize() {
        return getRaceId().getSizeId().getSizeName();
    }

    @Override
    public String getSkills() {
        return "隠れ身: " + getSkillTotalCheckModifierById(4) + "<br>"
                + "聞き耳: " + getSkillTotalCheckModifierById(7) + "<br>"
                + "交渉: " + getSkillTotalCheckModifierById(13) + "<br>"
                + "視認: " + getSkillTotalCheckModifierById(14) + "<br>"
                + "忍び足: " + getSkillTotalCheckModifierById(15) + "<br>"
                + "真意看破: " + getSkillTotalCheckModifierById(21) + "<br>"
                + "捜索: " + getSkillTotalCheckModifierById(28);
    }

    @Override
    public String getSpecialAttack() {
        return "";
    }

    @Override
    public String getSpecialQualities() {
        return "";
    }

    private String getType() {
        return getRaceId().getRaceName();
    }

    @Override
    public String getBaseAttackGrapple() {
        StringBuilder str = new StringBuilder();
        str.append(getBaseAttackTotal() < 0 ? "-" : "+");
        str.append(getBaseAttackTotal());
        str.append("/");
        str.append(getGrappleBonus() < 0 ? "-" : "+");
        str.append(getGrappleBonus());
        return str.toString();
    }

    @Override
    public String getSpaceAndReach() {
        return characterRecord.getRaceId().getSizeId().getContactSpace()
                + "フィート/"
                + characterRecord.getRaceId().getSizeId().getReach()
                + "フィート";

    }

    @Override
    public String getSizeAndType() {
        return getSize() + "サイズの" + getType();
    }

    @Override
    public String getTreasure() {
        return "";
    }

    @Override
    public String getAlignment() {
        return characterRecord.getAlignmentId().getAlignmentName();
    }

    @Override
    public String getAdvancement() {
        return "";
    }

    @Override
    public Integer getLevelAdjustment() {
        return 0;
    }

    public List<CharacterSkillRecord> getCharacterSkillRecordList() {
        return characterRecord.getCharacterSkillRecordList();
    }
    
    public List<CharacterAbilityRecord> getCharacterAbilityRecordList(){
        return characterRecord.getCharacterAbilityRecordList();
    }
    
    public Date getSaveTime() {
        return characterRecord.getSaveTime();
    }
}
