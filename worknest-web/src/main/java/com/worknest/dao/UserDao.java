package com.worknest.dao;

import com.worknest.model.User;
import java.util.List;

public interface UserDao {
    void save(User user);
    void update(User user);
    void delete(Integer id);
    User findById(Integer id);
    User findByEmail(String email);
    List<User> findAll();
}
