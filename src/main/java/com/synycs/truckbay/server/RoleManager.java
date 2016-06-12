package com.synycs.truckbay.server;

import java.io.Serializable;

/**
 * Created by hadoop on 29/05/15.
 * Interface which manages role and id of users and company
 */
public interface RoleManager extends Serializable{
    public String getName();
    public String getRole();
    public String getLoginName();
    public int totalTrucks();
    public boolean isMobileVerified();
    public boolean isFraud();
}
