package com.crud.util;

import com.crud.dao.abstraction.UserDao;
import com.crud.dao.impl.UserDaoHibernateImpl;
import com.crud.dao.impl.UserDaoJDBCImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class UserDaoFactory {
    private static UserDaoFactory instance;

    private UserDaoFactory() {
    }

    public static UserDaoFactory getInstance() {
        if (instance == null) {
            instance = new UserDaoFactory();
        }
        return instance;
    }

    public UserDao getUserDao() throws DBException{
        if (PropertyReader.getDaoType() == DaoType.HIBERNATE) {
            return new UserDaoHibernateImpl(getSessionFactory(DBHelper.getInstance().getConfiguration()));
        } else {
            return new UserDaoJDBCImpl(DBHelper.getInstance().getConnection());
        }
    }

    private SessionFactory getSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}
