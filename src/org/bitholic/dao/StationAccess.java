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
public class StationAccess {
    public static Session session;
    public static Gson gson;
    public static void main(String[] args){
        System.out.println(getStations());
    }

    public static String getStations(){
        String output;
        session = HibernateUtil.getSession();
        Query query1, query2;

        try {
            query1 = session.createQuery("select count(*) from Station");
            query2 = session.createQuery("from Station");
            //获取总条数
            Transaction transaction = session.beginTransaction();
            Long sum = (Long) query1.uniqueResult();

            List<Station> stations = query2.list();
            transaction.commit();
            StationJson stationJson = new StationJson(1,sum,stations);
            gson = new GsonBuilder().serializeNulls().create() ;
            output = gson.toJson(stationJson);
        } catch (Exception e) {
            output = "{\"state\":2, \"info\":\"发生错误!\"}";
        }
        return output;
    }
}
