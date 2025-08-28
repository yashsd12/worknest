package com.worknest.dao;

import com.worknest.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session s() { return sessionFactory.getCurrentSession(); }

    @Override public void save(User user)   { s().save(user); }
    @Override public void update(User user) { s().update(user); }

    @Override
    public void delete(Integer id) {
        User u = findById(id);
        if (u != null) s().delete(u);
    }

    @Override
    public User findById(Integer id) {
        return s().get(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        return (User) s().createQuery("from User u where u.email=:e")
                .setParameter("e", email)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return s().createQuery("from User").list();
    }
}
