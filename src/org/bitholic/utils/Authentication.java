package org.bitholic.utils;


import org.bitholic.dao.Account;
import org.bitholic.dao.AccountAccess;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.MessageDigest;

public class Authentication {
    public static boolean authenticate(String token, String uid, String passwd, String date){
        String str = uid + passwd + date;
        String computeToken = stringMD5(str);
        return token.equals(computeToken);
    }

    public static String stringMD5(String input) {
        String secretKey = "shendadaisadog";
        input += secretKey;
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = input.getBytes();
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            return byteArrayToHex(resultByteArray);
        }catch(Exception e){
            return null;
        }
    }

    public static boolean identityVerify(Request request, Response response) {
        return false;
    }

    public static Account identityVerify(HttpServletRequest request, HttpServletResponse response) {
        Account account = new Account();
        try {
            Cookie[] cookies = request.getCookies();
            String token = null;
            String name = null;
            String passwd = null;
            String expireTime = null;
            int currentTime = Math.round(System.currentTimeMillis() / 1000);
            for (int i = 0; i < cookies.length; i++) {
                Cookie c = cookies[i];
                if (c != null && c.getName().equals("name")) {
                    name = c.getValue();
                }
                if (c != null && c.getName().equals("token")) {
                    token = c.getValue();
                }
                if (c != null && c.getName().equals("expireTime")) {
                    expireTime = c.getValue();
                }
            }
            //token未过期,并且正确.
            if (expireTime != null && currentTime < Integer.parseInt(expireTime)) {
                Account account1 = AccountAccess.getAccount(name);
                passwd = account1.getPasswd();
                String newToken = stringMD5(name + passwd);
                if (newToken.equals(token)) {
                    account = account1;
                }
            }
            if (account != null) {
                int maxAge = 7*24*60*60;
                if(account.getRemember() == 0) {
                    maxAge = 60*60;
                }
                int expire = maxAge + Math.round(System.currentTimeMillis()/1000);
                Cookie expireCookie = new Cookie("expireTime",String.valueOf(expire));
                expireCookie.setMaxAge(maxAge);
                response.addCookie(expireCookie);
            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

    public static String stringSHA1(String input){
        String sha1 = null;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(input.getBytes("UTF-8"));
            sha1 = byteArrayToHex(crypt.digest());
        }catch(Exception e){
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteArrayToHex(byte[] byteArray){
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for(byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>>4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }
}
