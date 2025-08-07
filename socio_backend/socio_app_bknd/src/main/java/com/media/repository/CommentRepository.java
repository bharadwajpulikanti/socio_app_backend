package com.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
