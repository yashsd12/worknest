package com.worknest.service;

import com.worknest.model.User;
import java.util.List;

public interface UserService {
    User login(String email, String password);
    void register(User user);
    List<User> all();
    User byId(Integer id);
    void update(User user);
    void delete(Integer id);
}
