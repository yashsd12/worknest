package com.worknest.dao;

import com.worknest.model.Task;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired private SessionFactory sessionFactory;
    private Session s(){ return sessionFactory.getCurrentSession(); }

    @Override public void save(Task task) { s().save(task); }
    @Override public Task findById(Integer id){ return s().get(Task.class,id); }
    @Override @SuppressWarnings("unchecked")
    public List<Task> findAll(){ return s().createQuery("from Task").list(); }
    @Override public void delete(Integer id){
        Task t = findById(id);
        if(t!=null) s().delete(t);
    }
}
