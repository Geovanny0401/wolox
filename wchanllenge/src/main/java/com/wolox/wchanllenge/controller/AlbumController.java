package com.wolox.wchanllenge.controller;

import com.wolox.wchanllenge.model.Album;
import com.wolox.wchanllenge.service.IAlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@Api(tags = "Albums of External Api")
public class AlbumController {

    @Autowired
    private final IAlbumService iAlbumService;

    public AlbumController(IAlbumService iAlbumService) {
        this.iAlbumService = iAlbumService;
    }

    @GetMapping
    @ApiOperation(value = "Get All Albums", response = Album[].class)
    public List<Album> listarAlbums() {
        return iAlbumService.listar();
    }

    @GetMapping("/all/user")
    @ApiOperation(value = "Get All Albums By Id", response = Album[].class)
    public List<Album> listarAlbumUserPorId(@PathVariable(value = "userId") Long userId) {
        return iAlbumService.listarUserId(userId);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Get Album By Id", response = Album.class)
    public Album listarAlbumPorId(@PathVariable(value = "id") Long albumId) {
        return iAlbumService.listarId(albumId);
    }

}
