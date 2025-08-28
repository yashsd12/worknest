package com.worknest.service;

import com.worknest.dao.CommentDao;
import com.worknest.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired private CommentDao commentDao;

    @Override public void add(Comment c){ commentDao.save(c); }
    @Override public List<Comment> byAssignment(Integer assignmentId){ return commentDao.findByAssignment(assignmentId); }
}
