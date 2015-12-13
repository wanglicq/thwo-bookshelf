package com.thoughtworks.jimmy.controller;

import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.resource.BookResource;
import com.thoughtworks.jimmy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookShelfController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resources<BookResource>> query() {

        List<BookResource> bookResourceList = new ArrayList<>();

        for (Book book : bookService.findAll()) {
            bookResourceList.add(new BookResource(book));
        }

        Resources<BookResource> resources = new Resources<>(bookResourceList,
                ControllerLinkBuilder.linkTo(BookShelfController.class).withSelfRel());

        return ResponseEntity.ok(resources);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody Book book) {

        Book savedBook = bookService.create(book);

        Link linkToNewBook = new BookResource(savedBook).getLink("self");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", linkToNewBook.getHref() );

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
    public ResponseEntity<BookResource> get(@PathVariable String isbn) {

        BookResource bookResource = new BookResource(bookService.findByIsbn(isbn));

        return new ResponseEntity<>(bookResource, HttpStatus.OK);

    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable String isbn, @RequestBody Book book) {

        if (isbn.equals(book.getIsbn())) {
            bookService.edit(book);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String isbn) {

        final Book bookToDelete = bookService.findByIsbn(isbn);
        if ( bookToDelete != null ) {
            bookService.delete(bookToDelete);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
