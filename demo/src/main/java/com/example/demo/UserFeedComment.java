package com.example.demo;

public class UserFeedComment {
    private long commentID;
    private String commentBody;
    private long userID;
    private String name;

    public UserFeedComment(long commentID, String commentBody, long userID, String name) {
        this.commentID = commentID;
        this.commentBody = commentBody;
        this.userID = userID;
        this.name = name;
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

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
