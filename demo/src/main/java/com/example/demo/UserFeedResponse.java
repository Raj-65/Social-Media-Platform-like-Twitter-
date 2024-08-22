package com.example.demo;

import java.util.List;

public class UserFeedResponse {
    private List<UserFeedPost> posts;

    public UserFeedResponse(List<UserFeedPost> posts) {
        this.posts = posts;
    }

    public List<UserFeedPost> getPosts() {
        return posts;
    }

    public void setPosts(List<UserFeedPost> posts) {
        this.posts = posts;
    }
}
