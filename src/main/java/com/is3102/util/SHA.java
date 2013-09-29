package com.is3102.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: NguyenTranQuan
 * Date: 9/18/13
 * Time: 1:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class SHA {


    public SHA(){
    }

    public String convert(String value){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(value.getBytes("UTF-8"));
            StringBuilder stringBuilder=  new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            return "";
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
           return "";
        }
    }
}
