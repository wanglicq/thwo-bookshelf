package com.thoughtworks.jimmy.resource;

import com.thoughtworks.jimmy.controller.BookShelfController;
import com.thoughtworks.jimmy.model.Book;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Relation(value="book", collectionRelation="books")
public class BookResource extends ResourceSupport {

    private final Book book;

    public BookResource(final Book book) {
        this.book = book;
        this.add(linkTo(methodOn(BookShelfController.class).get(book.getIsbn())).withSelfRel());
        this.add(linkTo(BookShelfController.class).withRel("books"));
    }

    public Book getBook() {
        return book;
    }
}