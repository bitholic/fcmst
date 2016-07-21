<%@ page import="org.bitholic.utils.Authentication" %>
<%@ page import="org.bitholic.utils.CaptchaServiceSingleton" %>
<%@ page import="com.octo.captcha.service.CaptchaServiceException" %>
<%@ page import="org.bitholic.dao.Account" %>
<%@ page import="org.bitholic.dao.AccountAccess" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    /*
    从cookie中取出用户的id, 到数据库取到对应的密码
    比较用户名和密码是否一致 - "用户名或密码错误"
    发放签名: token = md5(userID, tokenExpiryTime, password, secretKey);
    给客户端发放token,同时设置userID和tokenExpiryTime cookie.
    以后客户端在访问时回传这三个id.
    首先验证是否过期,然后根据cookie重新计算token值,如果匹配则验证通过.

    expireTime cookie在随后的访问中要一直更新.

    */
    String name = request.getParameter("name");
    String passwd = request.getParameter("password");
    String captchaID = request.getSession().getId();
    String captchaValue = request.getParameter("captcha");
    Boolean isCaptchaCorrect = Boolean.FALSE;
    try{
        isCaptchaCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaID,captchaValue);
    }catch (CaptchaServiceException e){}

    if(!isCaptchaCorrect){
        Account account = AccountAccess.getAccount(name);
        if(account != null && passwd.equals(account.getPasswd())){
            String remember = request.getParameter("remember");
            int maxAge = 7*24*60*60;          //记住登录状态一周
            int longAge = 365*24*60*60;
            if(!remember.equals("true")){
                maxAge = 60*60;               //记住登录状态一个小时
            }
            //修改表
            int expireTime = maxAge + Math.round(System.currentTimeMillis()/1000);
            String s = name + passwd;
            String token = Authentication.stringMD5(s);
            Cookie cookie1 = new Cookie("name",name);
            Cookie cookie2 = new Cookie("token", token);
            Cookie cookie3 = new Cookie("expireTime", String.valueOf(expireTime));
            cookie1.setMaxAge(longAge);
            cookie2.setMaxAge(longAge);
            cookie3.setMaxAge(maxAge);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
            out.print("{\"state\":1,\"info\":\"登录成功!\"}");
        }else{
            out.print("{\"state\":2,\"info\":\"账号或密码错误!\"}");
        }
    }else{
        out.print("{\"state\":3,\"info\":\"验证码错误!\"}");
    }
%>
