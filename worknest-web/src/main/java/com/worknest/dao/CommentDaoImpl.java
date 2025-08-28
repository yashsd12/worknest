package com.worknest.dao;

import com.worknest.model.Comment;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired private SessionFactory sessionFactory;
    private Session s(){ return sessionFactory.getCurrentSession(); }

    @Override public void save(Comment c){ s().save(c); }

    @Override @SuppressWarnings("unchecked")
    public List<Comment> findByAssignment(Integer assignmentId){
        return s().createQuery("from Comment c where c.assignment.id=:aid order by c.createdAt desc")
                .setParameter("aid", assignmentId)
                .list();
    }
}
