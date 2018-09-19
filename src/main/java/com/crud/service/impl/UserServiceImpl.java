package com.crud.service.impl;

import com.crud.model.User;
import com.crud.service.abstraction.UserService;
import com.crud.util.DBException;
import com.crud.util.UserDaoFactory;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public User get(long id) {
        User user = null;
        try {
            user = UserDaoFactory.getInstance().getUserDao().get(id);
        }
        catch (DBException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByLogin(String login) {
        User user = null;
        try {
            user = UserDaoFactory.getInstance().getUserDao().getByLogin(login);
        }
        catch (DBException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        try {
            UserDaoFactory.getInstance().getUserDao().save(user);
        }
        catch (DBException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try {
            UserDaoFactory.getInstance().getUserDao().delete(user);
        }
        catch (DBException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            UserDaoFactory.getInstance().getUserDao().update(user);
        }
        catch (DBException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = null;
        try {
            users = UserDaoFactory.getInstance().getUserDao().getAll();
        }
        catch (DBException ex) {
            ex.printStackTrace();
        }
        return users;
    }
}
