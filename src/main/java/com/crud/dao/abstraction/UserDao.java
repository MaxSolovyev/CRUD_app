package com.crud.dao.abstraction;

import com.crud.model.User;

import java.util.List;

public interface UserDao {
    User get(long id);
    void save(User user);
    void delete(User user);
    void update(User user);
    List<User> getAll();
}
