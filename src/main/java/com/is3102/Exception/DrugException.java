/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.Exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author user
 */
@ApplicationException(rollback=true)
public class DrugException extends Exception {
    public DrugException(){}
    public DrugException(String message){
        super(message);
    }
    
}
