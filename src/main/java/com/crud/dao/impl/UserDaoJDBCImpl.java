package com.crud.dao.impl;

import com.crud.dao.abstraction.UserDao;
import com.crud.dao.executor.Executor;
import com.crud.model.User;
import org.hibernate.HibernateException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Executor executor;

    public UserDaoJDBCImpl(Connection connection) {
        executor = new Executor(connection);
    }

    public User get(long id) throws HibernateException {
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

    public void save(User user) {
        executor.execUpdate("INSERT INTO user (name, login, password) VALUES ('" + user.getName() + "','"+user.getLogin()+"','"+user.getPassword()+"')");
    }

    public void delete(User user) {
        executor.execUpdate("DELETE FROM user WHERE id = '" + user.getId() + "'");
    }

    public void update(User user) {
        executor.execUpdate("UPDATE user SET name = '" + user.getName() + "', login = '" + user.getLogin() + "', password = '" + user.getPassword() + "' WHERE id = " + user.getId());
    }

    public List<User> getAll() {
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
