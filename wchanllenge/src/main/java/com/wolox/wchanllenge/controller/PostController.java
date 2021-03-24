package com.wolox.wchanllenge.controller;

import com.wolox.wchanllenge.model.Post;
import com.wolox.wchanllenge.model.User;
import com.wolox.wchanllenge.service.IPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Api(tags = "Posts of External Api")
public class PostController {

    @Autowired
    private final IPostService iPostService;

    public PostController(IPostService iPostService) {
        this.iPostService = iPostService;
    }

    @GetMapping
    @ApiOperation(value = "Get All Posts", response = Post[].class)
    public List<Post> getAllPosts() {
        return iPostService.listar();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Post By Id", response = User.class)
    public Post getPostById(@PathVariable(value = "id") Long postId) {
        return iPostService.listarId(postId);
    }
}
