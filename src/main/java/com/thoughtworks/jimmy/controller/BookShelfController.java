package com.thoughtworks.jimmy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.thoughtworks.jimmy.entity.BookEntity;
import com.thoughtworks.jimmy.service.BookService;

//@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/books")
public class BookShelfController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET, value = "/title/{title}")
    public Iterable<BookEntity> queryByTitle(@PathVariable String title) {
        return bookService.findByTitle(title);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/category/{categoryName}")
    public Iterable<BookEntity> queryByCategoryName(@PathVariable String categoryName) {
        return bookService.findByCategoryName(categoryName);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<BookEntity> query() {

        return bookService.findAll();

    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody BookEntity book) {

        bookService.create(book);

    }

    @RequestMapping(value = "{isbn}", method = RequestMethod.GET)
    public BookEntity get(@PathVariable String isbn) {

        return bookService.findByIsbn(isbn);

    }

    @RequestMapping(value = "{isbn}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String isbn, @RequestBody BookEntity book) {

        if (isbn.equals(book.getIsbn())) {
            bookService.edit(book);
        }

    }

    @RequestMapping(value = "{isbn}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {

        bookService.delete(isbn);

    }

}
