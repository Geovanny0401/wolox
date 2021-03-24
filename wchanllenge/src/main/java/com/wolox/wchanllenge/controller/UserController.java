package com.wolox.wchanllenge.controller;

import com.wolox.wchanllenge.model.User;
import com.wolox.wchanllenge.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "Users of External Api")
public class UserController {

    @Autowired
    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping
    @ApiOperation(value = "Get All User", response = User[].class)
    public List<User> listar() {
        return iUserService.listar();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Get User By Id", response = User.class)
    public User listaPorId(@PathVariable(value = "id") Long userId) {
        return iUserService.listarId(userId);
    }
}
