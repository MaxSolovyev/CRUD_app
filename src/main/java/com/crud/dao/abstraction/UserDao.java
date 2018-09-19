package com.crud.dao.abstraction;

import com.crud.model.User;
import com.crud.util.DBException;

import java.util.List;

public interface UserDao {
    User get(long id) throws DBException;
    User getByLogin(String login) throws DBException;
    void save(User user) throws DBException;
    void delete(User user) throws DBException;
    void update(User user) throws DBException;
    List<User> getAll() throws DBException;
}
