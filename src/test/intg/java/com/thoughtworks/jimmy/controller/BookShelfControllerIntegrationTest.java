package com.thoughtworks.jimmy.controller;

import static java.lang.String.format;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.google.gson.Gson;
import com.thoughtworks.jimmy.SpringBootWebApplicationTests;
import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.repository.BookRepository;

public class BookShelfControllerIntegrationTest extends SpringBootWebApplicationTests{


    @Autowired
    private BookShelfController bookShelfController;

    @Autowired
    private BookRepository bookRepository;

    private Book commonBook;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookShelfController).build();
        commonBook = new Book("isbn", "book name", "author", 13.3);
    }

    @Test
    public void should_find_book_by_isbn_when_the_book_exists() throws Exception {
        Book savedBook = bookRepository.save(commonBook);

        mockMvc.perform(get(format("/books/%s",  savedBook.getIsbn())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value(savedBook.getIsbn()))
                .andExpect(jsonPath("$.title").value(savedBook.getTitle()))
                .andExpect(jsonPath("$.author").value(savedBook.getAuthor()))
                .andExpect(jsonPath("$.price").value(13.3));
    }

    @Test
    public void should_save_book_successfully() throws Exception {
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(commonBook)))
                .andExpect(status().isCreated());
    }
}