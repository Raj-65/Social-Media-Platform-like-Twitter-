package com.example.demo.repo;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostOrderByDateDesc(Post post);
    // Define custom queries or methods if needed
}
