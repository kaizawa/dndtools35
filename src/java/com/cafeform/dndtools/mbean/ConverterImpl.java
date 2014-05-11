package com.cafeform.dndtools.mbean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public abstract class ConverterImpl<T> implements Converter {

    @Override
    abstract public Object getAsObject(FacesContext context, UIComponent component, String value);
    @Override 
    abstract public String getAsString(FacesContext facesContext, UIComponent component, Object object);    

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }
}