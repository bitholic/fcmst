package org.bitholic.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by bitholic on 16/7/5.
 * 返回Hibernate session,多线程安全的.
 */
public class HibernateUtil {
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();
    private static SessionFactory sessionFactory;

    static{
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            sessionFactory = cfg.buildSessionFactory();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Session getSession(){
        Session session = (Session) THREAD_LOCAL.get();
        if(session == null){
            session = sessionFactory.openSession();
            THREAD_LOCAL.set(session);
        }
        return session;
    }
}
