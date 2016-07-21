package org.bitholic.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.HashMap;

/**
 * Created by bitholic on 16/7/2.
 */
public class DBConnector {
    private static final String url =
            "jdbc:mysql://localhost:3306/User?user=root&password=letsbegin&useUnicode=true&characterEncoding=utf-8"
            + "&autoReconnect=true&useSSL=false";

    public static HashMap<String,String> getAccount(String name){
        HashMap<String,String> output = new HashMap<String,String>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String sql = "SELECT * FROM account WHERE name='" + name + "'";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                output.put("name",rs.getString(1));
                output.put("passwd",rs.getString(2));
                output.put("privilege",String.valueOf(rs.getInt(3)));
                output.put("messageNumber", String.valueOf(rs.getInt(4)));
            }
            conn.close();
            if(output.isEmpty()){
                return null;
            }else {
                return output;
            }
        } catch (Exception e) {
            return null;
        }

    }

    public static void main(String[] args){
        HashMap<String,String> result = getAccount("admin");
        System.out.println(result);
    }

    public static String getException(Exception e) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        e.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception ex) {

        }
        return ret;
    }
}
