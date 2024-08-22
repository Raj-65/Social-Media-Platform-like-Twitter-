package com.example.demo;

public class CommentRequest {
    private String commentBody;
    private long userID;
    private long postID;
    private long commentID; // Only used for editing

    // Constructors, getters, and setters
    public CommentRequest() {
    }

    public CommentRequest(String commentBody, long userID, long postID, long commentID) {
        this.commentBody = commentBody;
        this.userID = userID;
        this.postID = postID;
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public long getCommentID() {
        return commentID;
    }

    public void setCommentID(long commentID) {
        this.commentID = commentID;
    }
}
