package com.thoughtworks.jimmy.service;

import com.thoughtworks.jimmy.entity.BookEntity;
import com.thoughtworks.jimmy.entity.CategoryEntity;

public interface BookService {

    Iterable<BookEntity> findAll();

    BookEntity findByIsbn(String isbn);

    void create(BookEntity book);

    void delete(String isbn);

    void edit(BookEntity book);

    Iterable<BookEntity> findByTitle(String title);

    Iterable<BookEntity> findByCategoryName(String name);

    CategoryEntity findCategoryByIsbn(String isbn);
}
