package com.worknest.service;

import com.worknest.dao.UserDao;
import com.worknest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired private UserDao userDao;

    @Override
    public User login(String email, String password){
        User u = userDao.findByEmail(email);
        if(u!=null && u.getPassword().equals(password)) return u;
        return null;
    }

    @Override public void register(User user){ userDao.save(user); }
    @Override public List<User> all(){ return userDao.findAll(); }
    @Override public User byId(Integer id){ return userDao.findById(id); }
    @Override public void update(User user){ userDao.update(user); }
    @Override public void delete(Integer id){ userDao.delete(id); }
}
