package com.crud.dao.impl;

import com.crud.dao.abstraction.UserDao;
import com.crud.dao.executor.Executor;
import com.crud.model.User;
import com.crud.service.DBService;
import com.crud.util.DBHelper;
import com.crud.util.PropertyReader;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private boolean isHibernate;
    private Executor executor;

    public UserDaoImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        isHibernate =  "hibernate".equals(PropertyReader.getProperties("used.tech"));
        if (!isHibernate) {
            executor = new Executor(DBHelper.getConnection());
        }
    }

    public User get(long id) throws HibernateException {
        if (isHibernate) {
            return (User) DBService.getSessionFactory().openSession().get(User.class, id);
        } else {
            return executor.execQuery("SELECT * FROM user WHERE id='" + id + "'", result -> {
                if (result.next()) {
                    return  new User(
                            result.getLong(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4)
                    );
                } else {
                    return null;
                }
            });

        }
    }

    public void save(User user) {
        if (isHibernate) {
            SessionFactory sessionFactory = DBService.getSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction ts = session.beginTransaction();
            session.save(user);
            ts.commit();
            session.close();
        } else {
            executor.execUpdate("INSERT INTO user (name, login, password) VALUES ('" + user.getName() + "','"+user.getLogin()+"','"+user.getPassword()+"')");
        }
    }

    public void delete(User user) {
        if (isHibernate) {
            Session session = DBService.getSessionFactory().openSession();
            Transaction ts = session.beginTransaction();
            session.delete(user);
            ts.commit();
            session.close();
        } else {
            executor.execUpdate("DELETE FROM user WHERE id = '" + user.getId() + "'");
        }
    }

    public void update(User user) {
        if (isHibernate) {
            Session session = DBService.getSessionFactory().openSession();
            Transaction ts = session.beginTransaction();
            session.update(user);
            ts.commit();
            session.close();
        } else {
            executor.execUpdate("UPDATE user SET name = '" + user.getName() + "', login = '" + user.getLogin() + "', password = '" + user.getPassword() + "' WHERE id = " + user.getId());
        }
    }

    public List<User> getAll() {
        if (isHibernate) {
            List<User> users = DBService.getSessionFactory().openSession().createQuery("from User").list();
            return users;
        } else {
            return executor.execQuery("SELECT * FROM user;",
                    result -> {
                        List<User> users = new ArrayList<>();
                        while (result.next()) {
                            users.add(
                                    new User(result.getLong(1),
                                            result.getString(3),
                                            result.getString(2),
                                            result.getString(4)
                                    )
                            );
                        }
                        return users;
                    });
        }
    }

}
