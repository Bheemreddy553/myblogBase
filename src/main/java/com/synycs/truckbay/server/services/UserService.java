package com.synycs.truckbay.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hadoop on 23/05/15.
 * to handle all customer care services
 */
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserService(){

    }
    private static UserService instance=new UserService();
    public static UserService getInstance(){
        return instance;
    }










}
