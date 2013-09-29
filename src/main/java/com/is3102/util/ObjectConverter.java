package com.is3102.util;

import com.is3102.entity.EmployeeGroup;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.HashMap;

@FacesConverter("com.is3102.util.ObjectConverter")
public class ObjectConverter implements Converter {

    private static HashMap<String, EmployeeGroup> map = new HashMap<String, EmployeeGroup>();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        EmployeeGroup EmployeeGroup = map.get(value);
        return EmployeeGroup;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        EmployeeGroup EmployeeGroup = (EmployeeGroup)value;
        map.put(EmployeeGroup.getId().toString(), EmployeeGroup);
        return EmployeeGroup.getId().toString();
    }
}
