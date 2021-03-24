package com.wolox.wchanllenge.dao;

import com.wolox.wchanllenge.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentDAO extends JpaRepository<Comment, Long> {
}
