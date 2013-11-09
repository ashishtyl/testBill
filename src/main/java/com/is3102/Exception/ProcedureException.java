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
public class ProcedureException extends Exception {
    public ProcedureException(){}
    public ProcedureException(String message){
        super(message);
    }
    
}