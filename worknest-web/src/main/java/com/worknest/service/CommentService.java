package com.worknest.service;

import com.worknest.model.Comment;
import java.util.List;

public interface CommentService {
    void add(Comment c);
    List<Comment> byAssignment(Integer assignmentId);
}
