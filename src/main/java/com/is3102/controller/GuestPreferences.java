package com.is3102.controller;

/**
 * Created with IntelliJ IDEA.
 * User: NguyenTranQuan
 * Date: 9/18/13
 * Time: 5:45 AM
 * To change this template use File | Settings | File Templates.
 */
import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class GuestPreferences implements Serializable {

    private String theme = "aristo"; //default

    public String getTheme() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if(params.containsKey("theme")) {
            theme = params.get("theme");
        }

        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}