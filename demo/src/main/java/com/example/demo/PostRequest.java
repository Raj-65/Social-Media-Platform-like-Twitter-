package com.example.demo;

public class PostRequest {
    private String postBody;
    private long userID;
    private long postID; // Only used for editing

    // Constructors, getters, and setters
    public PostRequest() {
    }

    public PostRequest(String postBody, long userID, long postID) {
        this.postBody = postBody;
        this.userID = userID;
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
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
}