package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.AlignmentMaster;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "alignmentMasterConverter", forClass = AlignmentMaster.class)
public class AlignmentMasterConverter extends ConverterImpl
{
    @Override
    public Object getAsObject (FacesContext facesContext, UIComponent component, String value)
    {
        if (value == null || value.length() == 0)
        {
            return null;
        }
        ConverterBean controller = (ConverterBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "converterBean");
        return controller.getAlignmentMaster(getKey(value));
    }

    @Override
    public String getAsString (FacesContext facesContext, UIComponent component, Object object)
    {
        if (object == null)
        {
            return null;
        }
        if (object instanceof AlignmentMaster)
        {
            AlignmentMaster o = (AlignmentMaster) object;
            return getStringKey(o.getId());
        } else if (object instanceof String)
        {
            return object.toString();
        } else
        {
            throw new IllegalArgumentException("Type of object: "
                    + object.getClass().getName() + "; expected type: " + AlignmentMaster.class.getName());
        }
    }
}
