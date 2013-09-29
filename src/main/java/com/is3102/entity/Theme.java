package com.is3102.entity;

/**
 * Created with IntelliJ IDEA.
 * User: NguyenTranQuan
 * Date: 9/18/13
 * Time: 5:41 AM
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;

public class Theme implements Serializable {

    private String name;

    private String image;

    public Theme() {}

    public Theme(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}