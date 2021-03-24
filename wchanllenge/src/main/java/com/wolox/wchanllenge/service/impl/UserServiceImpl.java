package com.wolox.wchanllenge.service.impl;

import com.wolox.wchanllenge.exception.ModeloNotFoundException;
import com.wolox.wchanllenge.model.User;
import com.wolox.wchanllenge.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@PropertySource("classpath:apiexternas.properties")
@Service
public class UserServiceImpl implements IUserService {

    private static final RestTemplate restTemplate = new RestTemplate();

    @Value("${apiendpoint.users.url}")
    private String apiendpointUsersUrl;

    public List<User> listar() {
        ResponseEntity<List<User>> response;
        try {
            response = restTemplate.exchange(
                    apiendpointUsersUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<User>>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(UserServiceImpl.class.toString());
        }
        return response.getBody();
    }

    @Override
    public List<User> listarCommentsPorNombre(String name) {
        return null;
    }

    public User listarId(long userId) {
        ResponseEntity<User> response;
        try {
            response = restTemplate.exchange(
                    apiendpointUsersUrl + "/" +
                            userId, HttpMethod.GET, null,
                    new ParameterizedTypeReference<User>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(UserServiceImpl.class.toString());
        }
        return response.getBody();
    }

    @Override
    public List<User> listarUserId(long id) {
        return null;
    }
}
