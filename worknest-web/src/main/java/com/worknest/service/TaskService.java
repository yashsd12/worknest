package com.worknest.service;

import com.worknest.model.*;
import java.util.Date;
import java.util.List;

public interface TaskService {
    void createTaskAndAssignToUsers(Task task, List<Integer> userIds, Date start, Date due);
    List<Task> allTasks();
    List<Assignment> assignmentsForUser(Integer userId);
    void updateAssignmentStatus(Integer assignmentId, Status status);
    List<Assignment> assignmentsForTask(Integer taskId);
    long count(Status status);
}
