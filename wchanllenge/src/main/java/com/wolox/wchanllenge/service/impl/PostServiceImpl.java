package com.wolox.wchanllenge.service.impl;

import com.wolox.wchanllenge.exception.ModeloNotFoundException;
import com.wolox.wchanllenge.model.Post;
import com.wolox.wchanllenge.service.IPostService;
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
public class PostServiceImpl implements IPostService {

    private static final RestTemplate restTemplate = new RestTemplate();

    @Value("${apiendpoint.posts.url}")
    private String apiendpointPostsUrl;

    @Value("${apiendpoint.posts.user.url}")
    private String apiendpointPostsUserUrl;

    @Override
    public Post listarId(long id) {
        ResponseEntity<Post> response;
        try {
            response = restTemplate.exchange(
                    apiendpointPostsUrl + "/" +
                            id, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Post>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(PostServiceImpl.class.toString());
        }
        return response.getBody();
    }

    @Override
    public List<Post> listarUserId(long id) {
        ResponseEntity<List<Post>> response;
        try {
            response = restTemplate.exchange(
                    apiendpointPostsUserUrl + id, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Post>>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(PostServiceImpl.class.toString());
        }
        return response.getBody();
    }

    @Override
    public List<Post> listar() {
        ResponseEntity<List<Post>> response;
        try {
            response = restTemplate.exchange(
                    apiendpointPostsUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Post>>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(PostServiceImpl.class.toString());
        }
        return response.getBody();
    }

    @Override
    public List<Post> listarCommentsPorNombre(String name) {
        return null;
    }
}
