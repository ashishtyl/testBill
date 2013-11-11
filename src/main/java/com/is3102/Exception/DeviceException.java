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
public class DeviceException extends Exception {
    public DeviceException(){}
    public DeviceException(String message){
        super(message);
    }
    
}
