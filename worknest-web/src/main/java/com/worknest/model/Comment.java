package com.worknest.model;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "comments")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "assignment_id", nullable=false)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @Column(length=2000, nullable=false)
    private String commentText;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    public Comment() {}

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Assignment getAssignment() { return assignment; }
    public void setAssignment(Assignment assignment) { this.assignment = assignment; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
