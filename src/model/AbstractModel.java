package model;

import entities.*;
import java.io.*;
import java.util.*;
import org.hibernate.*;

@SuppressWarnings("unchecked")
public abstract class AbstractModel<T> {

    private Class<T> entityClass;
    protected final SessionFactory sessionFactory = HibernateUtil
            .getSessionFactory();

    public AbstractModel(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public AbstractModel() {}

    public List<T> findAll() {

        	
            List<T> liste = new ArrayList<T>();
            Transaction trns = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                trns = session.beginTransaction();
                liste = session.createQuery("from " + entityClass.getName()).list();
            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                session.flush();
                session.close();
            }
            return liste;
        
        
    }

    public void update(T instance) {

        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(instance);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void delete(T instance) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.delete(instance);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void create(T instance) {
        Transaction trns = null;
        int idSaved=-1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(instance);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public T find(Object primarykey) {
        	
        	T object = null;
            Transaction trns = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                trns = session.beginTransaction();
                object = (T)session.get(entityClass,(Serializable) primarykey);
            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                session.flush();
                session.close();
            }
            return object;
        
        
    }
}