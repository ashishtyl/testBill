package com.is3102.service;

import com.is3102.entity.EmployeeGroup;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class EmployeeGroupService extends DataAccessService<EmployeeGroup> implements Serializable{

    public EmployeeGroupService(){
        super(EmployeeGroup.class);
    }
}
