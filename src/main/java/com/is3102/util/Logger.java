package com.is3102.util;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple logger class
 * @author Emre Simtay <emre@simtay.com>
 */

public class Logger {

    public Logger(){

    }

    public void log(String message){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String path = "/admin/log.txt";
        File file = new File(request.getServletContext().getRealPath(path));
        System.out.println(request.getServletContext().getRealPath(path));
        try{
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(message + "\n");
            bw.close();
        }
        catch (Exception e){
            // write to server log if we can not write in log file.
            System.out.println("Exception" + e.toString());
        }
    }
}