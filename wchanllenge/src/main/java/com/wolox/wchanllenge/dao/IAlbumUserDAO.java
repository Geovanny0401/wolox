package com.wolox.wchanllenge.dao;

import com.wolox.wchanllenge.model.AlbumUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAlbumUserDAO extends JpaRepository<AlbumUser, Long> {

    //Optional<AlbumUser> buscarAlbumIdPorUserId(Long albumId, Long userId);

}
