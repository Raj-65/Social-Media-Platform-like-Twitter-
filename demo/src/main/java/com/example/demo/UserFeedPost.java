package com.example.demo;

import java.util.List;

public class UserFeedPost {
    private long postID;
    private String postBody;
    private String date;
    private List<UserFeedComment> comments;

    public UserFeedPost(long postID, String postBody, String date, List<UserFeedComment> comments) {
        this.postID = postID;
        this.postBody = postBody;
        this.date = date;
        this.comments = comments;
    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<UserFeedComment> getComments() {
        return comments;
    }

    public void setComments(List<UserFeedComment> comments) {
        this.comments = comments;
    }
}
