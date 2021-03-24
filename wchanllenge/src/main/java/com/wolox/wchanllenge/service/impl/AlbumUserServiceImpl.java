package com.wolox.wchanllenge.service.impl;

import com.wolox.wchanllenge.dao.IAlbumUserDAO;
import com.wolox.wchanllenge.dao.IUserDAO;
import com.wolox.wchanllenge.exception.ModeloNotFoundException;
import com.wolox.wchanllenge.model.AlbumUser;
import com.wolox.wchanllenge.model.TypeOfAccess;
import com.wolox.wchanllenge.model.User;
import com.wolox.wchanllenge.service.IAlbumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumUserServiceImpl implements IAlbumUserService{

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IAlbumUserDAO albumUserDAO;

    public Optional<List<User>> getUsersByAlbumAndTypeAccess(Long albumId, Long typeIdAccess) {
        TypeOfAccess typeOfAccess = TypeOfAccess.listarPorAccessId(typeIdAccess);
        if (typeOfAccess.equals(TypeOfAccess.NONE)) {
            throw new ModeloNotFoundException(TypeOfAccess.class.toString());
        }
        return Optional.of(
                userDAO.findByAlbumIdAndTypeAccess(albumId, typeOfAccess).stream()
                        .collect(Collectors.toList())
        );
    }

    @Override
    public AlbumUser listarId(long id) {
        return null;
    }

    @Override
    public List<AlbumUser> listarUserId(long id) {
        return null;
    }

    @Override
    public List<AlbumUser> listar() {
        return null;
    }

    @Override
    public List<AlbumUser> listarCommentsPorNombre(String name) {
        return null;
    }
}
