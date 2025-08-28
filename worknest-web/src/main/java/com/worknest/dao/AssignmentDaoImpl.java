package com.worknest.dao;

import com.worknest.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AssignmentDaoImpl implements AssignmentDao {

    @Autowired private SessionFactory sessionFactory;
    private Session s(){ return sessionFactory.getCurrentSession(); }

    @Override public void save(Assignment a){ s().save(a); }
    @Override public void update(Assignment a){ s().update(a); }
    @Override public Assignment findById(Integer id){ return s().get(Assignment.class,id); }

    @Override @SuppressWarnings("unchecked")
    public List<Assignment> findByUserId(Integer userId){
        return s().createQuery("from Assignment a join fetch a.task where a.user.id=:uid")
                .setParameter("uid", userId)
                .list();
    }

    @Override @SuppressWarnings("unchecked")
    public List<Assignment> findByTaskId(Integer taskId){
        return s().createQuery("from Assignment a join fetch a.user where a.task.id=:tid")
                .setParameter("tid", taskId)
                .list();
    }

    @Override
    public long countByStatus(Status status){
        Long c = (Long) s().createQuery("select count(a.id) from Assignment a where a.status=:st")
                .setParameter("st", status)
                .uniqueResult();
        return c==null?0:c;
    }

    @Override
    public long countByUser(Integer userId){
        Long c = (Long) s().createQuery("select count(a.id) from Assignment a where a.user.id=:uid")
                .setParameter("uid", userId)
                .uniqueResult();
        return c==null?0:c;
    }
}
