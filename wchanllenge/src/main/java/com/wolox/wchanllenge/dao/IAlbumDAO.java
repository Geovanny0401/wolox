package com.wolox.wchanllenge.dao;

import com.wolox.wchanllenge.model.Album;
import com.wolox.wchanllenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAlbumDAO extends JpaRepository<Album, Long> {

}
