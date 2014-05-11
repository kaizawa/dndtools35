
package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.ejb.AbilityMasterFacade;
import com.cafeform.dndtools.ejb.AlignmentMasterFacade;
import com.cafeform.dndtools.ejb.ArmMasterFacade;
import com.cafeform.dndtools.ejb.ArmType1MasterFacade;
import com.cafeform.dndtools.ejb.ArmType2MasterFacade;
import com.cafeform.dndtools.ejb.ArmType3MasterFacade;
import com.cafeform.dndtools.ejb.BonusRankMasterFacade;
import com.cafeform.dndtools.ejb.CampaignMasterFacade;
import com.cafeform.dndtools.ejb.ClassMasterFacade;
import com.cafeform.dndtools.ejb.DamageTypeMasterFacade;
import com.cafeform.dndtools.ejb.DiceMasterFacade;
import com.cafeform.dndtools.ejb.GenderMasterFacade;
import com.cafeform.dndtools.ejb.RaceMasterFacade;
import com.cafeform.dndtools.ejb.ReligionMasterFacade;
import com.cafeform.dndtools.ejb.SaveMasterFacade;
import com.cafeform.dndtools.ejb.SizeMasterFacade;
import com.cafeform.dndtools.ejb.SkillMasterFacade;
import com.cafeform.dndtools.ejb.TypeMasterFacade;
import com.cafeform.dndtools.entity.AlignmentMaster;
import com.cafeform.dndtools.entity.ArmType1Master;
import com.cafeform.dndtools.entity.ArmType2Master;
import com.cafeform.dndtools.entity.ArmType3Master;
import com.cafeform.dndtools.entity.DamageTypeMaster;
import com.cafeform.dndtools.entity.DiceMaster;
import com.cafeform.dndtools.entity.SizeMaster;
import com.cafeform.dndtools.entity.TypeMaster;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@SessionScoped
public class ConverterBean implements Serializable {
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
    @EJB private TypeMasterFacade typeMasterFacade;
    @EJB private ArmType1MasterFacade armType1MasterFacade;
    @EJB private ArmType2MasterFacade armType2MasterFacade;
    @EJB private ArmType3MasterFacade armType3MasterFacade; 
    
    public TypeMaster getTypeMaster(Integer id)
    {
        return typeMasterFacade.find(id);
    }
    
    @FacesConverter(forClass = TypeMaster.class)
    public static class TypeMasterConverter extends ConverterImpl {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConverterBean controller = (ConverterBean)facesContext.getApplication()
                .getELResolver().getValue(facesContext.getELContext(), null, "converterBean");
            return controller.getTypeMaster(getKey(value));
        }
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TypeMaster) {
                TypeMaster o = (TypeMaster) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                    + object.getClass().getName() + "; expected type: " + TypeMaster.class.getName());
            }
        }
    }
    
    public ArmType1Master getArmType1Master (Integer id)
    {
        return armType1MasterFacade.find(id);
    }

    @FacesConverter(forClass = ArmType1Master.class)
    public static class ArmType1MasterConverter extends ConverterImpl {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConverterBean controller = (ConverterBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "converterBean");
            return controller.getArmType1Master(getKey(value));
        }
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ArmType1Master) {
                ArmType1Master o = (ArmType1Master) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                    + object.getClass().getName() + "; expected type: " + ArmType1Master.class.getName());
            }
        }
    }
     
    public ArmType2Master getArmType2Master (Integer id)
    {
        return armType2MasterFacade.find(id);
    }

    @FacesConverter(forClass = ArmType2Master.class)
    public static class ArmType2MasterConverter extends ConverterImpl {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConverterBean controller = (ConverterBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "converterBean");
            return controller.getArmType2Master(getKey(value));
        }
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ArmType2Master) {
                ArmType2Master o = (ArmType2Master) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                    + object.getClass().getName() + "; expected type: " + ArmType2Master.class.getName());
            }
        }
    }
    
    public ArmType3Master getArmType3Master (Integer id)
    {
        return armType3MasterFacade.find(id);
    }

    @FacesConverter(forClass = ArmType3Master.class)
    public static class ArmType3MasterConverter extends ConverterImpl {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConverterBean controller = (ConverterBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "converterBean");
            return controller.getArmType3Master(getKey(value));
        }
        
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ArmType3Master) {
                ArmType3Master o = (ArmType3Master) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                    + object.getClass().getName() + "; expected type: " + ArmType3Master.class.getName());
            }
        }
    }

    public DiceMaster getDiceMaster(java.lang.Integer id) {
        return diceMasterFacade.find(id);
    }

    @FacesConverter(forClass = DiceMaster.class)
    public static class DiceMasterConverter extends ConverterImpl {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConverterBean controller = (ConverterBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "converterBean");
            return controller.getDiceMaster(getKey(value));
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DiceMaster) {
                DiceMaster o = (DiceMaster) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                    + object.getClass().getName() + "; expected type: " + DiceMaster.class.getName());
            }
        }
    }
    
    public DamageTypeMaster getDamageTypeMaster(java.lang.Integer id) {
        return damageTypeMasterFacade.find(id);
    }

    @FacesConverter(forClass = DamageTypeMaster.class)
    public class DamageTypeMasterConverter extends ConverterImpl {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConverterBean controller = (ConverterBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "converterBean");
            return controller.getDamageTypeMaster(getKey(value));
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DamageTypeMaster) {
                DamageTypeMaster o = (DamageTypeMaster) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " 
                    + object.getClass().getName() + "; expected type: " + DamageTypeMaster.class.getName());
            }
        }
    }
    
    public AlignmentMaster getAlignmentMaster (Integer id) 
    {
        return alignmentMasterFacade.find(id);
    }
    
    public SizeMaster getSizeMaster(Integer id) {
        return sizeMasterFacade.find(id);
    }

    @FacesConverter(forClass = SizeMaster.class)
    public class SizeMasterConverter extends ConverterImpl {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConverterBean controller = (ConverterBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "converterBean");
            return controller.getSizeMaster(getKey(value));
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SizeMaster) {
                SizeMaster o = (SizeMaster) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " 
                    + object.getClass().getName() + "; expected type: " + SizeMaster.class.getName());
            }
        }
    }
}
