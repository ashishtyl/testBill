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
public class ExistException extends Exception{
    public ExistException(){}
    public ExistException(String message){
        super(message);
    }
}
