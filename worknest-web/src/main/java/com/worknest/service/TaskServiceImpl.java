package com.worknest.service;

import com.worknest.dao.*;
import com.worknest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired private TaskDao taskDao;
    @Autowired private UserDao userDao;
    @Autowired private AssignmentDao assignmentDao;

    @Override
    public void createTaskAndAssignToUsers(Task task, List<Integer> userIds, Date start, Date due){
        taskDao.save(task);
        System.out.println("Task created: " + task.getTitle());
        for(Integer uid : userIds){
            User u = userDao.findById(uid);
            if(u!=null){
                Assignment a = new Assignment();
                a.setTask(task);
                a.setUser(u);
                a.setStartDate(start);
                a.setDueDate(due);
                a.setStatus(Status.PENDING);
                assignmentDao.save(a);
                System.out.println("Assigned to: " + u.getEmail());
            }
        }
    }

    @Override public List<Task> allTasks(){ return taskDao.findAll(); }
    @Override public List<Assignment> assignmentsForUser(Integer userId){ return assignmentDao.findByUserId(userId); }
    @Override public void updateAssignmentStatus(Integer assignmentId, Status status){
        Assignment a = assignmentDao.findById(assignmentId);
        if(a!=null){
            a.setStatus(status);
            assignmentDao.update(a);
            System.out.println("Status updated for assignment "+assignmentId+" -> "+status);
        }
    }
    @Override public List<Assignment> assignmentsForTask(Integer taskId){ return assignmentDao.findByTaskId(taskId); }
    @Override public long count(Status status){ return assignmentDao.countByStatus(status); }
}
