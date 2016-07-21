package org.bitholic.dao;

import com.google.gson.Gson;
import org.bitholic.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by bitholic on 16/7/14.
 */
public class AccountAccess {
    public static Session session;
    public static Gson gson;

    public static void main(String[] args){
        System.out.println(getAccount("admin").getPrivilege());
    }

    public static Account getAccount(String uid){
        session = HibernateUtil.getSession();
        Query query = session.createQuery("from Account where uid=?");
        query.setString(0,uid);
        Transaction transaction = session.beginTransaction();
        Account account = (Account) query.uniqueResult();
        transaction.commit();
        return account;
    }
}
