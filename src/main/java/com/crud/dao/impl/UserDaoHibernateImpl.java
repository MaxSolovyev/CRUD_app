package com.crud.dao.impl;

import com.crud.dao.abstraction.UserDao;
import com.crud.model.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User get(long id) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        User user =  (User) session.get(User.class, id);
        ts.commit();
        session.close();
        return user;
    }

    public User getByLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        Criteria userCriteria = session.createCriteria(User.class);
        userCriteria.add(Restrictions.eq("login", login));
        User user =  (User) userCriteria.uniqueResult();
        ts.commit();
        session.close();
        return user;
    }

    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(user);
        ts.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.delete(user);
        ts.commit();
        session.close();
    }

    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.update(user);
        ts.commit();
        session.close();
    }

    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        ts.commit();
        session.close();

        return users;
    }

}
