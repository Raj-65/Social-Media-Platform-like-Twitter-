package com.example.demo.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentID;

    @Column(columnDefinition = "TEXT")
    private String commentBody;
    @Temporal(TemporalType.TIMESTAMP)

    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User commentCreator;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {
    }
    public Comment(String commentBody, User commentCreator, Post post, Date date) {
        this.commentBody = commentBody;
        this.commentCreator = commentCreator;
        this.post = post;
        this.date = date;
    }

    public Comment(String commentBody, User commentCreator, Post post) {
        this.commentBody = commentBody;
        this.commentCreator = commentCreator;
        this.post = post;
    }



    public long getCommentID() {
        return commentID;
    }

    public void setCommentID(long commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public User getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(User commentCreator) {
        this.commentCreator = commentCreator;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

