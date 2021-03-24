package com.wolox.wchanllenge.service.impl;

import com.wolox.wchanllenge.exception.ModeloNotFoundException;
import com.wolox.wchanllenge.model.Album;
import com.wolox.wchanllenge.service.IAlbumService;
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
public class AlbumServiceImpl implements IAlbumService {

    private static final RestTemplate restTemplate = new RestTemplate();

    @Value("${apiendpoint.albums.user.url}")
    private String apiendpointAlbumUserUrl;

    @Value("${apiendpoint.albums.url}")
    private String apiendpointAlbumsUrl;


    public List<Album> listar() {
        ResponseEntity<List<Album>> response;
        try {
            response = restTemplate.exchange(
                    apiendpointAlbumsUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Album>>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(AlbumServiceImpl.class.toString());
        }
        return response.getBody();
    }

    @Override
    public List<Album> listarCommentsPorNombre(String name) {
        return null;
    }

    public List<Album> listarUserId(long userId) {
        ResponseEntity<List<Album>> response;
        try {
            response = restTemplate.exchange(
                    apiendpointAlbumUserUrl +
                            userId, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Album>>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(AlbumServiceImpl.class.toString());
        }
        return response.getBody();
    }

    public Album listarId(long albumId) {
        ResponseEntity<Album> response;
        try {
            response = restTemplate.exchange(
                    apiendpointAlbumsUrl + "/" +
                            albumId, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Album>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(AlbumServiceImpl.class.toString());
        }
        return response.getBody();
    }


}
