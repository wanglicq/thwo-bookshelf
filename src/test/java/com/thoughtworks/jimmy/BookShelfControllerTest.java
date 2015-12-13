package com.thoughtworks.jimmy;

import com.jayway.restassured.http.ContentType;
import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.service.BookService;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

// This is actually an integration test starting the full application
public class BookShelfControllerTest extends AbstractControllerTest {

    @Autowired
    BookService bookService;

    private List<Book> books;

    @Before
    public void setUp() {
        books = new ArrayList<>((Collection<? extends Book>) bookService.findAll());
    }

    @Test
    public void canListAll() {

        when().get("/books").
                then().statusCode(HttpStatus.SC_OK).and().contentType(ContentType.JSON).
                and().body("_embedded.books", hasSize(3)).
                and().body("_embedded.books.book.isbn", hasItems(books.stream().map(Book::getIsbn).toArray()));
    }

    @Test
    public void canShowOneBook() {
        final Book expectedBook = books.get(0);

        when().get("/books/" + expectedBook.getIsbn()).
                then().statusCode(HttpStatus.SC_OK).and().contentType(ContentType.JSON).
                and().body("book.isbn", equalTo(expectedBook.getIsbn())).
                and().body("book.name", equalTo(expectedBook.getName())).
                and().body("book.author", equalTo(expectedBook.getAuthor()));
//                and().body("book.price", equalTo(expectedBook.getPrice().toString()));
    }

}
