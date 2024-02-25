package com.example.dao;

import java.util.List;

public interface DaoI<T> {
    String create(T entity);
    T read(int id);
}
