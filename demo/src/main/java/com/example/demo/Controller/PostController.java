package com.example.demo.Controller;

import com.example.demo.PostRequest;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repo.PostRepository;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepo userRepository;

    // Create a new post
    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) {
        Optional<User> userOptional = userRepository.findById(postRequest.getUserID());
        if (userOptional.isPresent()) {
            Post post = new Post(postRequest.getPostBody(), userOptional.get(), new Date());
            postRepository.save(post);
            return ResponseEntity.ok("Post created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    // Retrieve an existing post
    @GetMapping("/post")
    public ResponseEntity<?> getPost(@RequestParam long postID) {
        Optional<Post> postOptional = postRepository.findById(postID);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            // Return post details if post exists
            return ResponseEntity.ok(post);
        } else {
            // Return error message if post does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }

    // Edit an existing post
    @PatchMapping("/post")
    public ResponseEntity<String> editPost(@RequestBody PostRequest postRequest) {
        Optional<Post> postOptional = postRepository.findById(postRequest.getPostID());
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setPostBody(postRequest.getPostBody());
            postRepository.save(post);
            return ResponseEntity.ok("Post edited successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }

    // Delete a post
    @DeleteMapping("/post")
    public ResponseEntity<String> deletePost(@RequestParam long postID) {
        Optional<Post> postOptional = postRepository.findById(postID);
        if (postOptional.isPresent()) {
            postRepository.deleteById(postID);
            return ResponseEntity.ok("Post deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }
}
