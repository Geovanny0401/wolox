package com.wolox.wchanllenge.dao;

import com.wolox.wchanllenge.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhotoDAO extends JpaRepository<Photo, Long> {
}
