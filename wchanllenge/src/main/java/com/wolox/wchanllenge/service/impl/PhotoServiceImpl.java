package com.wolox.wchanllenge.service.impl;

import com.wolox.wchanllenge.exception.ModeloNotFoundException;
import com.wolox.wchanllenge.model.Photo;
import com.wolox.wchanllenge.service.IAlbumService;
import com.wolox.wchanllenge.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PhotoServiceImpl implements IPhotoService {

    private static final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private final IAlbumService iAlbumService;

    @Value("${apiendpoint.photos.url}")
    private String apiendpointUrlPhotos;

    @Value("${apiendpoint.photos.album.url}")
    private String apiendpointAlbumPhotosUrl;

    public PhotoServiceImpl(IAlbumService iAlbumService) {
        this.iAlbumService = iAlbumService;
    }

    @Override
    public List<Photo> listar() {
        ResponseEntity<List<Photo>> response;
        try {
            response = restTemplate.exchange(
                    apiendpointUrlPhotos, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Photo>>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(PhotoServiceImpl.class.toString());
        }
        return response.getBody();
    }

    @Override
    public List<Photo> listarCommentsPorNombre(String name) {
        return null;
    }

    public Photo listarId(long photoId) {
        ResponseEntity<Photo> response;
        try {
            response = restTemplate.exchange(
                    apiendpointUrlPhotos + "/" +
                            photoId, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Photo>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(PhotoServiceImpl.class.toString());
        }
        return response.getBody();
    }

    @Override
    public List<Photo> listarUserId(long id) {
        List<Photo> photos = new ArrayList<>();
        try {
            iAlbumService.listarUserId(id).forEach(album -> {
                photos.addAll(Objects.requireNonNull(restTemplate.exchange(
                        (apiendpointAlbumPhotosUrl + album.getId()), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Photo>>() {
                        }
                ).getBody()));
            });
        } catch (HttpStatusCodeException ex) {
            throw new ModeloNotFoundException(PhotoServiceImpl.class.toString());
        }
        return photos;
    }
}
