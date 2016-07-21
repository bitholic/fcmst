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
public class RouteAccess {
    public static Session session;
    public static Gson gson;

    public static void main(String[] args){
        System.out.println(getRoutes());
    }

    public static String getRoutes(){
        String output;
        session = HibernateUtil.getSession();
        Query query1, query2;

        try {
            query1 = session.createQuery("select count(*) from Route");
            query2 = session.createQuery("from Route");
            //获取总条数
            Transaction transaction = session.beginTransaction();
            Long sum = (Long) query1.uniqueResult();

            List<Route> routes = query2.list();
            transaction.commit();
            RouteJson routeJson = new RouteJson(1,sum,routes);
            gson = new GsonBuilder().serializeNulls().create() ;
            output = gson.toJson(routeJson);
        } catch (Exception e) {
            output = "{\"state\":2, \"info\":\"发生错误!\"}";
        }
        return output;
    }
}
