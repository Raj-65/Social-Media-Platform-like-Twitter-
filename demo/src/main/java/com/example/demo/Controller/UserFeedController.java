package com.example.demo.Controller;

import com.example.demo.UserFeedComment;
import com.example.demo.UserFeedPost;
import com.example.demo.UserFeedResponse;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.repo.CommentRepository;
import com.example.demo.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserFeedController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/")
    public ResponseEntity<UserFeedResponse> getUserFeed() {
        // Retrieve all posts sorted by date in descending order
        List<Post> posts = postRepository.findAllByOrderByDateDesc();

        // Construct response object
        List<UserFeedPost> userFeedPosts = posts.stream()
                .map(this::getPostWithComments)
                .collect(Collectors.toList());

        UserFeedResponse userFeedResponse = new UserFeedResponse(userFeedPosts);

        return ResponseEntity.ok(userFeedResponse);
    }

    private UserFeedPost getPostWithComments(Post post) {
        List<Comment> comments = commentRepository.findByPostOrderByDateDesc(post);

        List<UserFeedComment> userFeedComments = comments.stream()
                .map(comment -> new UserFeedComment(comment.getCommentID(), comment.getCommentBody(),
                        comment.getCommentCreator().getUserID(), comment.getCommentCreator().getName()))
                .collect(Collectors.toList());

        return new UserFeedPost(post.getPostID(), post.getPostBody(), post.getDate(), userFeedComments);
    }
}
