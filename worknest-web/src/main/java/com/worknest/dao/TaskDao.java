package com.worknest.dao;

import com.worknest.model.Task;
import java.util.List;

public interface TaskDao {
    void save(Task task);
    Task findById(Integer id);
    List<Task> findAll();
    void delete(Integer id);
}
