package com.cafeform.dndtools.mbean;

import com.cafeform.dndtools.entity.SizeMaster;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "sizeMasterConverter", forClass = SizeMaster.class)
public class SizeMasterConverter extends ConverterImpl
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
        return controller.getSizeMaster(getKey(value));
    }

    @Override
    public String getAsString (FacesContext facesContext, UIComponent component, Object object)
    {
        if (object == null)
        {
            return null;
        }
        if (object instanceof SizeMaster)
        {
            SizeMaster o = (SizeMaster) object;
            return getStringKey(o.getId());
        } else if (object instanceof String)
        {
            return object.toString();
        } else
        {
            throw new IllegalArgumentException("Type of object: "
                    + object.getClass().getName() + "; expected type: " + SizeMaster.class.getName());
        }
    }
}
