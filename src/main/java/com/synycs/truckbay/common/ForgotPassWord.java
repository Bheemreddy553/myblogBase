package com.synycs.truckbay.common;

/**
 * Created by hadoop on 26/08/15.
 */

import java.security.SecureRandom;
import java.util.Random;

public class ForgotPassWord
{

    private static final Random RANDOM = new SecureRandom();
    /** Length of password. @see #generateRandomPassword() */
    public static final int PASSWORD_LENGTH = 6;
    /**
     * Generate a random String suitable for use as a temporary password.
     *
     * @return String suitable for use as a temporary password
     * @since 2.4
     */
    public static String generateRandomPassword()
    {
        // Pick from some letters that won't be easily mistaken for each
        // other. So, for example, omit o O and 0, 1 l and L.
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";
        StringBuilder passWord=new StringBuilder();
        int length=letters.length();


        for (int i=0; i<PASSWORD_LENGTH; i++)
        {
            int index = (int)(RANDOM.nextDouble()*length);
            passWord.append(letters.substring(index, index+1));
        }
        return passWord.toString();
    }
}
