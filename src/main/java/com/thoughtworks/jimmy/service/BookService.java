package com.thoughtworks.jimmy.service;

import com.thoughtworks.jimmy.model.Book;

public interface BookService {

    Iterable<Book> findAll();

    Book findByIsbn(String isbn);

    Book create(Book book);

    void delete(Book book);

    void edit(Book book);
}
