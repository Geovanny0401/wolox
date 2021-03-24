package com.wolox.wchanllenge.service.impl;


import com.wolox.wchanllenge.exception.ModeloNotFoundException;
import com.wolox.wchanllenge.model.Comment;
import com.wolox.wchanllenge.service.ICommentService;
import com.wolox.wchanllenge.service.IPostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@PropertySource("classpath:apiexternas.properties")
@Service
public class CommentServiceImpl implements ICommentService{

    private static final RestTemplate restTemplate = new RestTemplate();

    private final IPostService iPostService;

    @Value("${apiendpoint.comments.url}")
    private String apiendpointCommentsUrl;

    @Value("${apiendpoint.comments.post.url}")
    private String apiendpointCommentsPostUrl;

    @Value("${apiendpoint.comments.name.url}")
    private String apiendpointCommentsNameUrl;

    public CommentServiceImpl(IPostService iPostService) {
        this.iPostService = iPostService;
    }

    @Override
    public Comment listarId(long id) {
        ResponseEntity<Comment> response;
        try {
            response = restTemplate.exchange(
                    apiendpointCommentsUrl + "/" +
                            id, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Comment>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(CommentServiceImpl.class.toString());
        }
        return response.getBody();
    }

    @Override
    public List<Comment> listarUserId(long id) {
        List<Comment> comments = new ArrayList<>();
        try {
            iPostService.listarUserId(id).forEach(postDTO -> {
                comments.addAll(Objects.requireNonNull(restTemplate.exchange(
                        (apiendpointCommentsPostUrl + postDTO.getId()), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Comment>>() {
                        }
                ).getBody()));
            });
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(CommentServiceImpl.class.toString());
        }
        return comments;
    }

    @Override
    public List<Comment> listar() {
        ResponseEntity<List<Comment>> response;
        try {
            response = restTemplate.exchange(
                    apiendpointCommentsUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Comment>>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(CommentServiceImpl.class.toString());
        }
        return response.getBody();
    }

    public List<Comment> listarCommentsPorNombre(String name) {
        ResponseEntity<List<Comment>> responseComments;
        try {
            responseComments = restTemplate.exchange(
                    apiendpointCommentsNameUrl + name, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Comment>>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(CommentServiceImpl.class.toString());
        }
        return responseComments.getBody();
    }
}
