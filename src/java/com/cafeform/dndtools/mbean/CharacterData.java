/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.ClassSkillMaster;
import com.cafeform.dndtools.entity.SkillSynergyMaster;
import com.cafeform.dndtools.entity.CharacterGrowthRecord;
import com.cafeform.dndtools.entity.CampaignMaster;
import com.cafeform.dndtools.entity.CharacterEquipment;
import com.cafeform.dndtools.entity.BonusRankMaster;
import com.cafeform.dndtools.entity.RaceAbilityMaster;
import com.cafeform.dndtools.entity.SkillMaster;
import com.cafeform.dndtools.entity.CharacterRecord;
import com.cafeform.dndtools.entity.RaceSaveMaster;
import com.cafeform.dndtools.entity.ClassMaster;
import com.cafeform.dndtools.entity.AbilityMaster;
import com.cafeform.dndtools.entity.CharacterSaveRecord;
import com.cafeform.dndtools.entity.GenderMaster;
import com.cafeform.dndtools.entity.CharacterSkillRecord;
import com.cafeform.dndtools.entity.CharacterSkillGrowthRecord;
import com.cafeform.dndtools.entity.ReligionMaster;
import com.cafeform.dndtools.entity.AlignmentMaster;
import com.cafeform.dndtools.entity.SaveMaster;
import com.cafeform.dndtools.entity.RaceMaster;
import com.cafeform.dndtools.entity.CharacterAbilityRecord;
import com.cafeform.dndtools.ejb.SkillMasterFacade;
import com.cafeform.dndtools.ejb.ClassSaveMasterFacade;
import com.cafeform.dndtools.ejb.RaceAbilityMasterFacade;
import com.cafeform.dndtools.ejb.GenderMasterFacade;
import com.cafeform.dndtools.ejb.AlignmentMasterFacade;
import com.cafeform.dndtools.ejb.CharacterGrowthRecordFacade;
import com.cafeform.dndtools.ejb.ReligionMasterFacade;
import com.cafeform.dndtools.ejb.SaveMasterFacade;
import com.cafeform.dndtools.ejb.CharacterSkillRecordFacade;
import com.cafeform.dndtools.ejb.ClassMasterFacade;
import com.cafeform.dndtools.ejb.ClassSkillMasterFacade;
import com.cafeform.dndtools.ejb.CharacterSkillGrowthRecordFacade;
import com.cafeform.dndtools.ejb.CharacterEquipmentFacade;
import com.cafeform.dndtools.ejb.CharacterRecordFacade;
import com.cafeform.dndtools.ejb.CharacterAbilityRecordFacade;
import com.cafeform.dndtools.ejb.AbilityMasterFacade;
import com.cafeform.dndtools.ejb.SkillSynergyMasterFacade;
import com.cafeform.dndtools.ejb.RaceMasterFacade;
import com.cafeform.dndtools.ejb.CharacterSaveRecordFacade;
import com.cafeform.dndtools.entity.ArmMaster;
import com.cafeform.dndtools.entity.CharacterArmRecord;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
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
    protected List<SkillMaster> skillList;
    protected List<AbilityMaster> abilityList;
    protected List<SaveMaster> saveList;
    
    public CharacterRecord getCharacterRecord() {
        return characterRecord;
    }

    private CharacterData() { }

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
        skillList = skillMasterFacade.findAll();
        abilityList = abilityMasterFacade.findAll();
        saveList = saveMasterFacade.findAll();
    }

    public Integer getSkillMiscModifierById(int index) {
        Integer result;
        List<CharacterSkillRecord> skillRecordList = characterRecord.getCharacterSkillRecordList();
        // skillTable ? RowIndex ? List ? index ??????????????
        result = skillRecordList.get(index).getMiscModifier();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    public void updateGrowthRecord() {

        List<CharacterGrowthRecord> searchedCharaGrowthList =
                characterGrowthRecordFacade.findByCharacter(characterRecord);
        List<CharacterGrowthRecord> characterGrowthList = new ArrayList<CharacterGrowthRecord>();

        OUTER:
        for (int i = 1; i < getLevel() + 1; i++) {
            for (CharacterGrowthRecord growth : searchedCharaGrowthList) {
                if (growth.getCharacterGrowthRecordPK().getCharacterLevel() == i) {
                    characterGrowthList.add(growth);
                    continue OUTER;
                }
            }
            CharacterGrowthRecord newRecord =
                    new CharacterGrowthRecord(characterRecord.getId().intValue(), i);
            try {
                characterGrowthRecordFacade.create(newRecord);
            } catch (Exception e) {
                context.addMessage("contents:contentGrid:label1", new FacesMessage(("Cannot update growth record")));
                return;
            }
            characterGrowthList.add(newRecord);
        }
        //??????????????
        characterRecord.setCharacterGrowthRecordList(characterGrowthList);

        /*
         * ????????????????
         */
        // ????????????
        List<CharacterSkillGrowthRecord> searchedCharaSkillGrowthList =
                characterSkillGrowthRecordFacade.findByCharacter(characterRecord);
        List<CharacterSkillGrowthRecord> characterSkillGrowthList = new ArrayList<CharacterSkillGrowthRecord>();
        // ????????????????????????
        for (int i = 1; i < getLevel() + 1; i++) {
            OUTER:
            for (SkillMaster skill : skillList) {
                for (CharacterSkillGrowthRecord skillgrowth : searchedCharaSkillGrowthList) {
                    if (skillgrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() == i && 
                        skillgrowth.getCharacterSkillGrowthRecordPK().getSkillId() == skill.getId().intValue()) {
                        //??????????
                        characterSkillGrowthList.add(skillgrowth);
                        continue OUTER;
                    }
                }
                //???????????? .
                CharacterSkillGrowthRecord newRecord =
                        new CharacterSkillGrowthRecord(characterRecord.getId().intValue(), i, skill.getId().intValue());

                //????????0????
                newRecord.setSkillPoint(0);
                try {
                    characterSkillGrowthRecordFacade.create(newRecord);
                } catch (Exception e) {
                    context.addMessage("contents:contentGrid:label1", 
                        new FacesMessage(("?????????????????????????")));
                    return;
                }

                characterSkillGrowthList.add(newRecord);
            }
        }
        characterRecord.setCharacterSkillGrowthRecordList(characterSkillGrowthList);

    }

    public Integer geHitPoint() {
        int totalHP = 0;
        List<CharacterGrowthRecord> growthList = characterRecord.getCharacterGrowthRecordList();

        int bonus = getAbilityModifierById(CON);
        for (CharacterGrowthRecord growth : growthList) {
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
        // skillTable ? RowIndex ? List ? index ??????????????
        skillRecord.get(index).setMiscModifier(skillMiscModifier);
    }

    public Integer getAbilityBaseById(int id) {
        // ??? ID ? 1-6 ???List ? ID ? 0 ????????? -1 ??
        Integer result;
        List<CharacterAbilityRecord> charAbilityList = characterRecord.getCharacterAbilityRecordList();
        CharacterAbilityRecord ability = charAbilityList.get(id - 1);
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

    public Integer getAbilityMiscModifierById(int id) {
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
    }

    public Integer getAbilityFeatModifierById(int id) {
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

    public Integer getAbilityRaceModifierById(int id) {
        AbilityMaster ability = abilityMasterFacade.find(id);
        if (ability == null) {
            // abilityTable ? RowIndex ????
            return 0;
        }
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

    public Integer getAbilityLevelModifierById(int id) {
        int modifier = 0;

        List<CharacterGrowthRecord> growthList =
                characterGrowthRecordFacade.findByCharacter(characterRecord);

        for (CharacterGrowthRecord growth : growthList) {
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel()) {
                break;
            }
            Integer enhancedAb = growth.getAbilityEnhancement();
            if (enhancedAb == null) {
                continue;
            }
            if (id == enhancedAb.intValue()) {
                modifier++;
            }
        }
        return modifier;
    }

    public Integer getAbilityTotalById(int id) {
        return getAbilityBaseById(id)
                + getAbilityRaceModifierById(id)
                + getAbilityFeatModifierById(id)
                + getAbilityMiscModifierById(id)
                + getAbilityLevelModifierById(id);
    }
    /*
     * ??? ???
     */

    public Integer getAbilityModifierById(int ability) {

        return (getAbilityTotalById(ability) / 2) - 5;
    }

    /**
     * ?? ???????
     */
    public Integer getSkillAbilityModifierById(int skill) {

        return getAbilityModifierById(skillList.get(skill-1).getAbilityId().getId());
    }
    
    /**
     * ?? ??????
     */
    public String getSkillAbilityNameById(int skill) {

        return skillList.get(skill-1).getAbilityId().getAbilityName();
    }
    /**
     * ?? ?????? ???
     */
    public String getSkillAbilityShortNameById(int skill) {

        String name;
        name = skillList.get(skill-1).getAbilityId().getAbilityName();
        return name.substring(0, 1);
    }
    
    /**
     * ?? ????
     */
    public Integer getSkillTotalPointById(int skill) {
        int point = 0;

        List<CharacterSkillGrowthRecord> skillGrowthlist = characterRecord.getCharacterSkillGrowthRecordList();
        for (CharacterSkillGrowthRecord skillGrowth : skillGrowthlist) {
            // ???????????????????????
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
     * ?? ???
     */

    public Integer getSkillTotalCheckModifierById(Integer index) {
        return getSkillAbilityModifierById(index)
                + getSkillTotalRankById(index)
                + getSkillMiscModifierById(index)
                + getSkillArmorModifierById(index)
                + getSkillSynergyModifierById(index);
    }

    /*
     * ?? ???????) ??????????????
     */
    protected Integer skillRank;
    protected Integer skillRankByLevelAndSkill;

    public Float getSkillRankByLevelAndSkill(CharacterGrowthRecord growth, SkillMaster skill) {
        int point = 0;
        float rank;
        ClassMaster klass = growth.getClassId();

        List<CharacterSkillGrowthRecord> skillgrowthlist = characterRecord.getCharacterSkillGrowthRecordList();
        for (CharacterSkillGrowthRecord skillGrowth : skillgrowthlist) {
            Integer level = skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel();
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
            return 0f;
        }
        if (isClassSkillByLevelAndSkill(growth, skill)) {
            rank = point;
        } else {
            rank = (new Float(point)) / 2f;
        }
        return rank;
    }

    public Integer getSkillTotalRankById(int skillId) {
        Float totalRank = 0f;

        SkillMaster skill = skillList.get(skillId-1);
        List<CharacterGrowthRecord> growthlist = characterRecord.getCharacterGrowthRecordList();
        for (CharacterGrowthRecord growth : growthlist) {
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel()) {
                break;
            }
            totalRank += getSkillRankByLevelAndSkill(growth, skill);
        }
        return (totalRank.intValue());
    }

    protected boolean skillAcceptNoRankBySkillId;

    public boolean isSkillAcceptNoRankBySkillId(int skillId) {
        SkillMaster skill = skillList.get(skillId-1);

        if (skill == null) {
            return false;
        }

        return (skill.getAcceptNoRank().intValue() == 1);
    }

    public boolean isClassSkillByClassAndSkill(ClassMaster klass, SkillMaster skill) {
        List<ClassSkillMaster> classSkillList = classSkillMasterFacade.findByClass(klass);
        for (ClassSkillMaster classSkill : classSkillList) {
            if (classSkill.getClassSkillMasterPK().getSkillId() == skill.getId().intValue()) {
                return true;
            }
        }
        return false;
    }

    public boolean isClassSkillByLevelAndSkill(CharacterGrowthRecord growthLevel, SkillMaster skill) {
        List<CharacterGrowthRecord> growthList = characterRecord.getCharacterGrowthRecordList();
        for (CharacterGrowthRecord growth : growthList) {
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() 
                > growthLevel.getCharacterGrowthRecordPK().getCharacterLevel()) {
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

    public boolean hasSkillRankBySkill(SkillMaster skill) {
        List<CharacterSkillGrowthRecord> skillGrowthList = characterRecord.getCharacterSkillGrowthRecordList();

        for (CharacterSkillGrowthRecord skillGrowth : skillGrowthList) {
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

    public Integer getSkillArmorModifierById(int skillId) {
        int result = 0;
        SkillMaster skill = skillList.get(skillId-1);

        if (skill.getArmorCheck() == 0) {
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
            result *= 2;
        }
        return result;
    }

    public Integer getSkillSynergyModifierById(int skillId) {
        int result = 0;
        SkillMaster skill = skillList.get(skillId-1);

        List<SkillSynergyMaster> synergyList = skillSynergyMasterFacade.findBySkill(skill);
        for (SkillSynergyMaster synergy : synergyList) {
            int affectedSkillId = synergy.getSkillSynergyMasterPK().getAffectedBy();
            int affectedSkillRank = getSkillTotalRankById(affectedSkillId);
            if (affectedSkillRank > 0) {
                result += (affectedSkillRank / 5) * 2;
            }
        }
        return result;
    }

    public Integer getSaveAbilityModifierById(int saveId) {

        return getAbilityModifierById(saveMasterFacade.find(saveId).getAbilityId().getId());
    }

    public Integer getSaveClassBonusById(int saveId) {
        int bonus = 0;

        SaveMaster save = saveMasterFacade.find(saveId);

        List<CharacterGrowthRecord> growthList = characterRecord.getCharacterGrowthRecordList();

            Map<Integer, Integer> classMap = new HashMap<Integer, Integer>();
        for (CharacterGrowthRecord growth : growthList) {
                if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel()) {
                break;
            }
            if (growth.getClassId() == null) {
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

        for (Map.Entry<Integer, Integer> classEntry : classMap.entrySet()) {
            Integer classId = classEntry.getKey();
            Integer lv = classEntry.getValue();

            BonusRankMaster rank = classSaveMasterFacade.findByClassAndSave(classMasterFacade.find(classId), save).getRankId();
            if (rank == null) {
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

    public Integer getSaveTotalById(int saveId) {
        return getSaveAbilityModifierById(saveId)
                + getSaveClassBonusById(saveId)
                + getSaveMiscModifierById(saveId)
                + getSaveRaceModifierById(saveId);
    }

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

    public Integer getInitiativeAbilityModifier() {
        return getAbilityModifierById(DEX);
    }

    protected Integer initiativeTotal;

    public Integer getInitiativeTotal() {
        return getInitiativeAbilityModifier() + getInitiativeFeatModifier() + getInitiativeMiscModifier();
    }

    protected Integer speed;

    public Integer getSpeedTotal() {
        return getSpeedRaceBasse() + getSpeedFeatModifier() + getSpeedMiscModifier();
    }

    @Override
    public String getSpeed() {
        StringBuilder str = new StringBuilder();
        str.append(getSpeedTotal());
        str.append("????(");
        str.append((int) getSpeedTotal() / 5);
        str.append("??)");
        return str.toString();
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    /*
     * ???? ??????
     */
    protected Integer speedRaceBasse;

    public Integer getSpeedRaceBasse() {
        RaceMaster race = characterRecord.getRaceId();
        if (race == null) {
            //???????????? ?????
            return new Integer(0);
        }
        return race.getSpeed();
    }
    /*
     * ???? ?????
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
     * ?? ??????
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
     * AC ???????(???)
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
     * ??AC<p> ?????????????????????????????????<p> ????????????????????????????
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
     * AC ?????
     */
    public Integer getAcAbilityModifier() {
        // ???? ID ? 2. ??????????????????????????????
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
     * AC ?????
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
     * AC ?????
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
     * AC ????
     */
    protected Integer acRace;

    public Integer getAcRaceModifier() {
        RaceMaster race = characterRecord.getRaceId();
        // ?????
        if (race == null) {
            return 0;
        }

        return race.getSizeId().getAcModifier();
    }
    /**
     * AC ???
     */
    protected Integer acMiscMod;

    public Integer getAcMiscMod() {
        return characterRecord.getAcMiscMod();
    }

    /**
     * ?????????????
     */
    public Integer getHitPointAbilityModifier() {
        // ???? ID ? 2
        return getAbilityModifierById(CON);
    }

    /**
     * ?????? ????????
     */
    public Integer getBaseAttackTotal() {
        int baseAttack = 0;
        //?????????????????????????
        List<CharacterGrowthRecord> growthList = characterRecord.getCharacterGrowthRecordList();
        Map<ClassMaster, Integer> classMap = new HashMap<ClassMaster, Integer>();
        for (CharacterGrowthRecord growth : growthList) {
            // ???????????????????????
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
            //????BAB????????????
            return (1 / 2) * lv;
        }
        Float result; // ????????
        switch (rank) {
            case 1: // ??
                return lv;
            case 2: //??
                result = lv * (3F / 4F);
                return result.intValue();
            case 3: // ??
                result = lv * (1F / 2F);
                return result.intValue();
            default: // ???
                return 0;
        }
    }

    /**
     * ?????? ????????
     */
    public Integer getRangeAttackBonus() {
        return getBaseAttackTotal() + getAbilityModifierById(DEX);
    }

    /**
     * ?????? ????????
     */
    public Integer getMeleeAttackBonus() {
        return getBaseAttackTotal() + getAbilityModifierById(STR);
    }    
    /*
     * ?????? ????????
     */

    public Integer getGrappleBonus() {
        return getMeleeAttackBonus();
    }
    /*
     * ?????? ????????
     */

    public Integer getAttackBonusStrengthBonus() {
        return getAbilityModifierById(STR);
    }
    /*
     * ?????? ????????
     */

    public Integer getAttackBonusDexBonus() {
        return getAbilityModifierById(DEX);
    }
    /**
     * ???? ?? TODO: ???
     */
    /*
     * public String getArm1 (){ ArmMaster arm1 =
     * characterRecord.getCharacterEquipment().getArm1(); if(arm1 != null) {
     * return arm1.getName(); } return "???"; } public String getArm2 (){
     * ArmMaster arm2 = characterRecord.getCharacterEquipment().getArm2();
     * if(arm2 != null) { return arm2.getName(); } return "???"; }
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
     * ?????
     */
    public String getLastChange() {
        Date date = characterRecord.getSaveTime();

        if (date == null) {
            return "--???--";
        }
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
        return fmt.format(date);
    }

    /**
     * ???<br>???
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
    
    /**
     * This method only return a list of CharacterGrowthRecode whose level
     * is less than or equal to current level 
     */
    public List<CharacterGrowthRecord> getCurrentCharacterGrowthRecordList() {
        List<CharacterGrowthRecord> totalList = characterRecord.getCharacterGrowthRecordList();
        List<CharacterGrowthRecord> currentList = new ArrayList();
        
        for (CharacterGrowthRecord growth : totalList) {
            // ???????????????????????
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getLevel()) {
                break;
            }
            currentList.add(growth);
        }
        return currentList;
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
        if (null == characterRecord.getCharacterName() ||
                "".equals(characterRecord.getCharacterName())) 
        {
            return "no name";
        } 
        else 
        {    
            return characterRecord.getCharacterName();
        }
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
        // Need to get player name via PlayerId class.
        // This method exists just for compatibility.
        return "";
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
        // Do nothing. PlayerName can't be set as string.
        // Need to select from player list.
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
        List<CharacterAbilityRecord> charAbilityList = characterRecord.getCharacterAbilityRecordList();
        List<CharacterSaveRecord> charSaveList = characterRecord.getCharacterSaveRecordList();

        //???????
        Date date = new Date();
        characterRecord.setSaveTime(date);

        //??
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
        for (CharacterAbilityRecord ability : charAbilityList) {
            characterAbilityRecordFacade.edit(ability);
        }
        for (CharacterSaveRecord save : charSaveList) {
            characterSaveRecordFacade.edit(save);
        }
    }

    public void remove() {
        characterRecordFacade.remove(characterRecord);
    }

    @Override
    public String getAbilities() {
        StringBuilder str = new StringBuilder();
        List<CharacterAbilityRecord> charAbilityList = characterRecord.getCharacterAbilityRecordList();
        for (CharacterAbilityRecord ability : charAbilityList) {
            str.append(getAbilityShortName(ability.getAbilityMaster())).append(" ")
                    .append(getAbilityTotalById(ability.getAbilityMaster().getId()))
                    .append("(")
                    .append(getAbilityModifierById(ability.getAbilityMaster()
                    .getId()))
                    .append(")<br>");
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
            // ???????????????????????
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() == getLevel()) {
                if (growth.getClassId() == null) {
                    return ("???");
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
        return "???: " + getSkillTotalCheckModifierById(4) + "<br>"
                + "???: " + getSkillTotalCheckModifierById(7) + "<br>"
                + "??: " + getSkillTotalCheckModifierById(13) + "<br>"
                + "??: " + getSkillTotalCheckModifierById(14) + "<br>"
                + "???: " + getSkillTotalCheckModifierById(15) + "<br>"
                + "????: " + getSkillTotalCheckModifierById(21) + "<br>"
                + "??: " + getSkillTotalCheckModifierById(28);
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
                + "????/"
                + characterRecord.getRaceId().getSizeId().getReach()
                + "????";

    }

    @Override
    public String getSizeAndType() {
        return getSize() + "????" + getType();
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
    
    /**
     * Return attack bonus and damage bonus for this character for given 
     * arms.
     * 
     * e.g. ??: +17/+12(d8+7 19-20/x2)(??+d6)
     * 
     * @param armRecord
     * @return modifiers
     */
    public String getAttackModifiers (CharacterArmRecord armRecord)
    {
        ArmMaster arm = armRecord.getArmId();
        String type3Name = null != arm.getArmType3() ? arm.getArmType3().getName() : "";        
        StringBuilder modifiers = new StringBuilder();
        modifiers.append(type3Name).append(" "); // ?????
        int baseAttack = getBaseAttackTotal();
        int attackBonus = 0;
        int numAttacks = ((baseAttack - 1) / 5) + 1;
        int enhancementBonus = null != arm.getEnhancementBonus() ? arm.getEnhancementBonus() : 0;
        int attackModifier = null != armRecord.getAttackModifier() ? armRecord.getAttackModifier() : 0;
        int damageModifier = null != armRecord.getDamageModifier()? armRecord.getDamageModifier() : 0;        
        int strengthDamageBonus = 0;
        
        /* Attack bonus & Streangth Damage bonus */
        if (1 == arm.getArmType3().getId()) {
            attackBonus = getMeleeAttackBonus();

            /* size modifier */
            if (null != arm.getSize()) {
                /* Calculate differnce of size between race and arm */
                int diff = Math.abs(getRaceId().getSizeId().getId() - arm.getSize().getId());
                if(0 != diff)
                {
                    /* Arm size is unmatch */
                    attackBonus -= (2 * diff);
                }
            }
            /* 
             * String damage bonus 
             * Bonus point is multipled by 1.5, if it two hand weapon
             */
            strengthDamageBonus = getAttackBonusStrengthBonus();        
            if (null != arm.getArmType2() && arm.getArmType2().getId() == 4) {
                strengthDamageBonus *= 1.5;
            }
        } else {
            attackBonus = getRangeAttackBonus();
            
            /* Streangth bonus/penalty for composite long bow */
            if (null != arm.getComposite_long_bow_streangth_bonus())
            {
                if (getAbilityModifierById(STR) < arm.getComposite_long_bow_streangth_bonus()){
                    attackBonus -=2;
                } 
                strengthDamageBonus += Math.min(getAbilityModifierById(STR),
                        arm.getComposite_long_bow_streangth_bonus());
            }
        }
        
        /* Is Masterwork ? */
        if (0 == enhancementBonus && arm.isMasterwork())
        {
            attackBonus++;
        }

        for(int i = 0 ; i < numAttacks ; i++)
        {
            if ( 0 != i ){
                modifiers.append("/");
            }
            /*
             * Base Attack Bonus + Magic + Modifier 
             */
            modifiers.append("+")
                .append(attackBonus + enhancementBonus + attackModifier - (i * 5));
        }
        
        /* Critical range */
        int threatRange = null != arm.getThreatRange() ? arm.getThreatRange() : 20;
        String threatRangStr;        
        if(20 == threatRange) 
        {
            threatRangStr = "20";
        }
        else 
        {
            threatRangStr = threatRange + "-20";
        }
        
        String diceType = null != arm.getDamageDiceType() ? arm.getDamageDiceType().getName() : "";
        
 

        /* Damange bonus & Critical area */ 
        modifiers.append("(")
            .append(arm.getDamageDiceNum())
            .append(diceType);
        int totalDamageModifier = strengthDamageBonus + enhancementBonus + damageModifier;
        /* Doesn't show modifier, if modifier is zero */
        if(totalDamageModifier > 0 ){
            modifiers.append("+")
                .append(strengthDamageBonus + enhancementBonus + damageModifier);
        }
        modifiers.append(" ")
            .append(threatRangStr).append("/x")
            .append(arm.getCriticalMultiplier())
            .append(")");

        return modifiers.toString();
    }
}
