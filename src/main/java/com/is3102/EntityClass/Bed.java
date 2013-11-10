/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ben
 */
@Entity
public class Bed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bedId;
    private String bedNo;
    private String roomNo;
    private String floor;

    public Bed() {
    }

    public void create(String bedNo, String roomNo, String floor) {
        this.setBedNo(bedNo);
        this.setRoomNo(roomNo);
        this.setFloor(floor);
    }

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}