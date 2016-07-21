package org.bitholic.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bitholic.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by bitholic on 16/7/13.
 */
public class DriverAccess {
    public static Session session;
    public static Gson gson;
    public static void main(String[] args){
        System.out.println(getDrivers());
    }

    public static String getDrivers(){
        String output;
        session = HibernateUtil.getSession();
        Query query1, query2;

        try {
            query1 = session.createQuery("select count(*) from Driver");
            query2 = session.createQuery("from Driver");
            //获取总条数
            Transaction transaction = session.beginTransaction();
            Long sum = (Long) query1.uniqueResult();

            List<Driver> drivers = query2.list();
            transaction.commit();

            DriverJson driverJson = new DriverJson(1,sum, drivers);
            gson = new GsonBuilder().serializeNulls().create();
            output = gson.toJson(driverJson);
        } catch (Exception e) {
            output = "{\"state\":2, \"info\":\"发生内部错误!\"}";
        }
        return output;
    }
}
