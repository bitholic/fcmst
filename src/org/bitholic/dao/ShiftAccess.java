package org.bitholic.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bitholic.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by bitholic on 16/7/12.
 */
public class ShiftAccess {
    public static Session session;
    public static void main(String[] args){
        //System.out.println(deleteShift(10));

        String s = "{\"shiftID\":\"21\",\"licensePlate\":\"京E1000\",\"startTime\":\"12:33\",\"routeName\":\"快三线正\",\"driverID\":\"2014211961\"}";
        System.out.println(addShift(s));
    }

    public static String getShifts(){
        session = HibernateUtil.getSession();

        Query query = session.createQuery("select count(*) from Shift");
        Transaction transaction = session.beginTransaction();
        Long sum = (Long)query.uniqueResult();
        transaction.commit();

        query = session.createQuery("from Shift");
        transaction.begin();
        List<Shift> shifts = query.list();
        transaction.commit();

        ShiftJson shiftJson = new ShiftJson(1,sum,shifts);
        Gson gson = new GsonBuilder().serializeNulls().create();
        String output = gson.toJson(shiftJson);
        return output;
    }

    public static String deleteShift(Integer shiftID){
        String output;
        try {
            session = HibernateUtil.getSession();
            Query query = session.createQuery("from Shift where shiftID=?");
            query.setInteger(0,shiftID);
            Shift shift = (Shift)query.uniqueResult();
            Transaction transaction = session.beginTransaction();
            session.delete(shift);
            transaction.commit();
            output = "{\"state\":1, \"info\":\"删除成功!\"}";
        }catch(Exception e){
            e.printStackTrace();
            output = "{\"state\":2, \"info\":\"删除失败!\"}";
        }
        return output;
    }

    public static String addShift(String jsonInput){
        String output = null;
        session = HibernateUtil.getSession();
        Gson gson = new Gson();
        try{
            Shift shift = gson.fromJson(jsonInput,Shift.class);
            Integer shiftID = shift.getShiftID();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Shift where shiftID=?");
            query.setInteger(0,shiftID);
            Shift shift1 = (Shift)query.uniqueResult();
            transaction.commit();

            if(shift1 == null){
                transaction.begin();
                session.save(shift);
                transaction.commit();
                output = "{\"state\":1, \"info\":\"添加成功!\"}";
            }else{
                output = "{\"state\":2, \"info\":\"班次名重复!\"}";
            }
        }catch(Exception e){
            output = "{\"state\":3, \"info\":\"发生错误!添加失败.\"}";
            e.printStackTrace();
        }
        return output;
    }
}
