package com.example.demo.Controller;

import com.example.demo.CommentRequest;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repo.CommentRepository;
import com.example.demo.repo.PostRepository;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PostRepository postRepository;

    // Create a new comment
//    @PostMapping("/comment")
//    public ResponseEntity<String> createComment(@RequestBody CommentRequest commentRequest) {
//        Optional<User> userOptional = userRepository.findById(commentRequest.getUserID());
//        if (userOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
//        }
//
//        Optional<Post> postOptional = postRepository.findById(commentRequest.getPostID());
//        if (postOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
//        }
//
//        Comment comment = new Comment(commentRequest.getCommentBody(), userOptional.get(), postOptional.get());
//        commentRepository.save(comment);
//        return ResponseEntity.ok("Comment created successfully");
//    }

    @PostMapping("/comment")
    public ResponseEntity<String> createComment(@RequestBody CommentRequest commentRequest) {
        try {
            Optional<User> userOptional = userRepository.findById(commentRequest.getUserID());
            if (!userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
            }

            Optional<Post> postOptional = postRepository.findById(commentRequest.getPostID());
            if (!postOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
            }

            Comment comment = new Comment(commentRequest.getCommentBody(), userOptional.get(), postOptional.get());
            commentRepository.save(comment);
            return ResponseEntity.ok("Comment created successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
    // Retrieve an existing comment
    @GetMapping("/comment")
    public ResponseEntity<?> getComment(@RequestParam long commentID) {
        Optional<Comment> commentOptional = commentRepository.findById(commentID);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            // Return comment details if comment exists
            return ResponseEntity.ok(comment);
        } else {
            // Return error message if user does not exist
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
//    @GetMapping("/comment")
//    public ResponseEntity<?> getComment(@RequestParam long commentID) {
//        Optional<Comment> commentOptional = commentRepository.findById(commentID);
//        if (commentOptional.isPresent()) {
//            Comment comment = commentOptional.get();
//
//            // Get comment creator details
//            User commentCreator = comment.getCommentCreator();
//
//            // Construct response manually in the desired format
//            Map<String, Object> response = new HashMap<>();
//
//            // Construct comment creator object
//            Map<String, Object> commentCreatorMap = new HashMap<>();
//            commentCreatorMap.put("userID", commentCreator.getUserID());
//            commentCreatorMap.put("name", commentCreator.getName());
//
//            response.put("commentCreator", commentCreatorMap);
//
//            // Construct comment object
//            Map<String, Object> commentMap = new HashMap<>();
//            commentMap.put("commentID", comment.getCommentID());
//            commentMap.put("commentBody", comment.getCommentBody());
//            commentMap.put("commentCreator", commentCreatorMap); // Nested comment creator
//            // You can add other fields as needed
//
//            // Add comment object to response
//            response.put("comment", commentMap);
//
//            // Return response
//            return ResponseEntity.ok(response);
//        } else {
//            // Return error message if comment does not exist
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
//        }
//    }

    // Edit an existing comment
    @PatchMapping("/comment")
    public ResponseEntity<String> editComment(@RequestBody CommentRequest commentRequest) {
        Optional<Comment> commentOptional = commentRepository.findById(commentRequest.getCommentID());
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setCommentBody(commentRequest.getCommentBody());
            commentRepository.save(comment);
            return ResponseEntity.ok("Comment edited successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
    }

    // Delete a comment
    @DeleteMapping("/comment")
    public ResponseEntity<String> deleteComment(@RequestParam long commentID) {
//        Optional<Comment> commentOptional = commentRepository.findById(commentID);
//        if (commentOptional.isPresent()) {
//            commentRepository.deleteById(commentID);
//            return ResponseEntity.ok("Comment deleted");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
//        }
        try {
            Optional<Comment> commentOptional = commentRepository.findById(commentID);
            if (commentOptional.isPresent()) {
                commentRepository.deleteById(commentID);
                return ResponseEntity.ok("Comment deleted");
            } else {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
