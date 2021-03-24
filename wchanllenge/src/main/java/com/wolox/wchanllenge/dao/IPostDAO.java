package com.wolox.wchanllenge.dao;

import com.wolox.wchanllenge.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostDAO extends JpaRepository<Post, Long> {
}
