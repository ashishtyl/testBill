/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import javax.ejb.Remote;

/**
 *
 * @author Swarit
 */
@Remote
public interface OrderEntryRemote {
    
    public void prescribeMedication(String name, Long dosage, int quantity, String details, Long totalPrice);
}
