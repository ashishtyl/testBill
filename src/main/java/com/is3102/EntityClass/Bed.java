/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Ben
 */
@Entity
public class Bed implements Serializable {

    @Id
    private Long bedNo;
    private String roomNo;
    private String floor;

    public Bed(){}

    public void create(Long bedNo, String roomNo, String floor){
        this.setRoomNo(roomNo);
        this.setBedNo(bedNo);
        this.setFloor(floor);
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Long getBedNo() {
        return bedNo;
    }

    public void setBedNo(Long bedNo) {
        this.bedNo = bedNo;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}