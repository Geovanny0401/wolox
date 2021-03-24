package com.wolox.wchanllenge.service;

import java.util.List;

public interface ICRUD<T>{


    T listarId(long id);

    List<T> listarUserId(long id);

    List<T> listar();

    List<T> listarCommentsPorNombre(String name);
}
