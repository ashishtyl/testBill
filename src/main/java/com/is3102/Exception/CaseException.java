/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.Exception;

import javax.ejb.ApplicationException;
/**
 *
 * @author Ben
 */
@ApplicationException(rollback=true)
public class CaseException extends Exception{
    
    public CaseException(){}
    public CaseException(String message){
        super(message);
    }
    
}