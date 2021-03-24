package com.wolox.wchanllenge.controller;

import com.wolox.wchanllenge.exception.ModeloNotFoundException;
import com.wolox.wchanllenge.model.Album;
import com.wolox.wchanllenge.model.AlbumUser;
import com.wolox.wchanllenge.model.TypeOfAccess;
import com.wolox.wchanllenge.model.User;
import com.wolox.wchanllenge.service.IAlbumUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/albumuser")
@Api(tags = "Albums Users Shared of External Api")
public class AlbumUserController {

    @Autowired
    private final IAlbumUserService iAlbumUserService;

    public AlbumUserController( IAlbumUserService iAlbumUserService) {
        this.iAlbumUserService = iAlbumUserService;
    }

    @GetMapping("/all/users")
    @ApiOperation(value = "Get all users with the access permission associated with an album", response = AlbumUser[].class)
    public ResponseEntity<List<User>> getAllUsersByAlbumAndAccessType(@PathParam("albumId") Long albumId,
                                                                      @PathParam("accessTypeId") Long accessTypeId) {
        return ResponseEntity.ok(iAlbumUserService.getUsersByAlbumAndTypeAccess(albumId, accessTypeId)
                .orElseThrow(() -> new ModeloNotFoundException(TypeOfAccess.class.toString())));
    }


}
