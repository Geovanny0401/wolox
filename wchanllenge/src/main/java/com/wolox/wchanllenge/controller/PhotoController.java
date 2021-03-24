package com.wolox.wchanllenge.controller;

import com.wolox.wchanllenge.model.Photo;
import com.wolox.wchanllenge.service.IPhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photos")
@Api(tags = "Photos of External Api")
public class PhotoController {

    @Autowired
    private final IPhotoService iPhotoService;

    public PhotoController(IPhotoService iPhotoService) {
        this.iPhotoService = iPhotoService;
    }

    @GetMapping
    @ApiOperation(value = "Get All Photos", response = Photo[].class)
    public List<Photo> listar() {
        return iPhotoService.listar();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Get Photo By Id", response = Photo.class)
    public Photo listarPorId(@PathVariable(value = "id") Long photoId) {
        return iPhotoService.listarId(photoId);
    }

    @GetMapping("/all/user")
    @ApiOperation(value = "Get All Photos By User Id", response = Photo[].class)
    public List<Photo> listarPhotosPorIdUser(@RequestParam("albumId") Long albumId) {
        return iPhotoService.listarUserId(albumId);
    }
}
