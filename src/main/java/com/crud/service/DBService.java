//package com.crud.service;
//
//import com.crud.dao.impl.UsersDAO;
//import com.crud.model.User;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.internal.SessionFactoryImpl;
//import org.hibernate.service.ServiceRegistry;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
///**
// * @author v.chibrikov
// *         <p>
// *         Пример кода для курса на https://stepic.org/
// *         <p>
// *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
// */
//public class DBService {
//    private static final String hibernate_show_sql = "true";
//    private static final String hibernate_hbm2ddl_auto = "create";
//
//    private final SessionFactory sessionFactory;
//
//    public DBService() {
//        Configuration configuration = getMySqlConfiguration();
////        Configuration configuration = getH2Configuration();
//        sessionFactory = createSessionFactory(configuration);
//    }
//
//    @SuppressWarnings("UnusedDeclaration")
//    private Configuration getMySqlConfiguration() {
//        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(User.class);
//
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/crud");
//        configuration.setProperty("hibernate.connection.username", "root");
//        configuration.setProperty("hibernate.connection.password", "root");
//        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
//        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
//        return configuration;
//    }
//
//    private Configuration getH2Configuration() {
//        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(User.class);
//
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
//        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
//        configuration.setProperty("hibernate.connection.username", "tully");
//        configuration.setProperty("hibernate.connection.password", "tully");
//        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
//        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
//        return configuration;
//    }
//
//
//    public User getUser(long id) throws DBException {
//        try {
//            Session session = sessionFactory.openSession();
//            UsersDAO dao = new UsersDAO(session);
//            User dataSet = dao.get(id);
//            session.close();
//            return dataSet;
//        } catch (HibernateException e) {
//            throw new DBException(e);
//        }
//    }
//
//    public long addUser(String name) throws DBException {
//        try {
//            Session session = sessionFactory.openSession();
//            Transaction transaction = session.beginTransaction();
//            UsersDAO dao = new UsersDAO(session);
//            long id = dao.insertUser(name);
//            transaction.commit();
//            session.close();
//            return id;
//        } catch (HibernateException e) {
//            throw new DBException(e);
//        }
//    }
//
//    public void printConnectInfo() {
//        try {
//            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
//            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
//            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
//            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
//            System.out.println("Driver: " + connection.getMetaData().getDriverName());
//            System.out.println("Autocommit: " + connection.getAutoCommit());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static SessionFactory createSessionFactory(Configuration configuration) {
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
//        builder.applySettings(configuration.getProperties());
//        ServiceRegistry serviceRegistry = builder.build();
//        return configuration.buildSessionFactory(serviceRegistry);
//    }
//}










package com.crud.service;

import com.crud.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBService {

    private static SessionFactory sessionFactory;

    private DBService() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = getMySqlConfiguration();
            sessionFactory = createSessionFactory(configuration);
        }
        return sessionFactory;
    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/crud");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
//        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);





        //        StandardServiceRegistry standardServiceRegistry =
//                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        Metadata metaData = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
//        SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();


//        StandardServiceRegistryBuilder stndserviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//
//        ServiceRegistry serviceRegistry = stndserviceRegistry.build();
//        return configuration.buildSessionFactory(serviceRegistry);


//        return sessionFactory;
    }
}
