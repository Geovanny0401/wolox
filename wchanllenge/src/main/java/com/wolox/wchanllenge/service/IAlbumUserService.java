package com.wolox.wchanllenge.service;

import com.wolox.wchanllenge.model.AlbumUser;
import com.wolox.wchanllenge.model.User;

import java.util.List;
import java.util.Optional;

public interface IAlbumUserService extends ICRUD<AlbumUser>{

    Optional<List<User>> getUsersByAlbumAndTypeAccess(Long albumId, Long accessTypeId);
}
