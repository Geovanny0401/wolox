package com.wolox.wchanllenge.controller;

import com.wolox.wchanllenge.model.Comment;
import com.wolox.wchanllenge.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@Api(tags = "Comments of External Api")
public class CommentController {

    @Autowired
    private final ICommentService iCommentService;

    public CommentController(ICommentService iCommentService) {
        this.iCommentService = iCommentService;
    }

    @GetMapping
    @ApiOperation(value = "Get All Comments", response = Comment[].class)
    public List<Comment> listar() {
        return iCommentService.listar();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Comment By Id", response = Comment.class)
    public Comment listarPorId(@PathVariable(value = "id") Long commentId) {
        return iCommentService.listarId(commentId);
    }

    @GetMapping("/all/name")
    @ApiOperation(value = "Get All Comments By Name", response = Comment[].class)
    public List<Comment> listarPorNombre(@RequestParam(value = "name") String name) {
        return iCommentService.listarCommentsPorNombre(name);
    }

    @GetMapping("/all/post")
    @ApiOperation(value = "Get All Comments By Post Id", response = Comment[].class)
    public List<Comment> listarPorPostId(@RequestParam(value = "postId") Long postId) {
        return iCommentService.listarUserId(postId);
    }
}
