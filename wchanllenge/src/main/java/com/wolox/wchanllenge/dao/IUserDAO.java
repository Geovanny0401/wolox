package com.wolox.wchanllenge.dao;

import com.wolox.wchanllenge.model.TypeOfAccess;
import com.wolox.wchanllenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDAO extends JpaRepository<User, Long> {

    @Query("SELECT au.user FROM AlbumUser AS au WHERE au.album.id = (:albumId) AND au.typeOfAccess = (:typeOfAccess)")
    List<User> findByAlbumIdAndTypeAccess(@Param("albumId") Long albumId,
                                          @Param("typeOfAccess") TypeOfAccess typeOfAccess);

}
