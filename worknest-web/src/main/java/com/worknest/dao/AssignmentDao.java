package com.worknest.dao;

import com.worknest.model.*;
import java.util.List;

public interface AssignmentDao {
    void save(Assignment a);
    Assignment findById(Integer id);
    List<Assignment> findByUserId(Integer userId);
    List<Assignment> findByTaskId(Integer taskId);
    long countByStatus(Status status);
    long countByUser(Integer userId);
    void update(Assignment a);
}
