package org.bitholic.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bitholic.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by bitholic on 16/7/11.
 */
public class EmployeeAccess {
    public static Session session;
    public static Gson gson;

    public static void main(String[] args){
        System.out.print(getAllEmployees());
    }

    public static String getAllEmployees(){
        session = HibernateUtil.getSession();
        gson = new GsonBuilder().serializeNulls().create();

        Query query = session.createQuery("from Employee ");
        List<Employee> employees = query.list();
        String output = gson.toJson(new EmployeeJson(1,(long)20,employees));
        return output;
    }
}
