package com.thoughtworks.jimmy.service;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import com.thoughtworks.jimmy.controller.BookShelfController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.thoughtworks.jimmy.entity.BookEntity;
import com.thoughtworks.jimmy.entity.CategoryEntity;
import com.thoughtworks.jimmy.repository.BookRepository;
import com.thoughtworks.jimmy.repository.CategoryRepository;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BookShelfController bookShelfController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_find_book_by_title_when_given_title() {
        Iterable<BookEntity> expectedBooks = asList(
                new BookEntity("12345", "Head Fist Java", "author", 55.0)
        );
        String title = "Java";
        when(bookRepository.findByTitle(title)).thenReturn(expectedBooks);

        Iterable<BookEntity> books = bookService.findByTitle(title);

        assertEquals(expectedBooks, books);

    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_contain_title_not_found() throws Exception {
        String title = "zz";
        when(bookRepository.findByTitle(title)).thenReturn(null);
        bookService.findByTitle(title);

    }

    @Test
    public void should_find_book_by_tag_when_given_valid_tag() {
        Iterable<BookEntity> expectedBooks = asList();
        CategoryEntity category = new CategoryEntity("B011", "IT", "This is a description");
        when(categoryRepository.findByName(category.getName())).thenReturn(category);
        when(bookRepository.findByCategoryCode(category.getCode())).thenReturn(expectedBooks);

        Iterable<BookEntity> books = bookService.findByCategoryName(category.getName());

        assertEquals(expectedBooks, books);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_tag_not_found() {
        String categoryName = "category name";
        when(categoryRepository.findByName(categoryName)).thenReturn(null);

        bookService.findByCategoryName(categoryName);
    }

    @Test
    public void should_find_book_by_isbn_when_given_isbn() throws Exception {
        BookEntity expectedBook = new BookEntity("001", "book1", "author1", 11.11);
        when(bookRepository.findOne("001")).thenReturn(expectedBook);
        BookEntity book = bookService.findByIsbn("001");
        assertThat(expectedBook, is(book));

    }

    @Test
    public void should_find_all_books_when_query_all() throws Exception {
        Iterable<BookEntity> expectedBooks = asList(
                new BookEntity("001", "book1", "author1", 11.11),
                new BookEntity("002", "book2", "author2", 22.22),
                new BookEntity("003", "book3", "author3", 33.33)
        );
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        Iterable<BookEntity> books = bookService.findAll();
        assertThat(expectedBooks, is(books));

    }

    @Test
    public void should_save_books_successfully_when_create_books() throws Exception {
        BookEntity createBook = new BookEntity("004", "book4", "author4", 44.44);
        bookService.create(createBook);
        when(bookRepository.findOne("004")).thenReturn(createBook);
        BookEntity getBook = bookService.findByIsbn("004");
        assertThat(getBook, is(createBook));

    }

    @Test(expected = RuntimeException.class)
    public void should_create_book_conflict_when_book_already_exists() throws Exception {
        bookRepository.save(asList(
                new BookEntity("001", "book1", "author1", 11.11),
                new BookEntity("002", "book2", "author2", 22.22),
                new BookEntity("003", "book3", "author3", 33.33)
        ));
        BookEntity newBook = new BookEntity("001", "book1", "author1", 11.11);
        when(bookRepository.exists(newBook.getIsbn())).thenReturn(true);
        bookService.create(newBook);
    }

    @Test
    public void should_update_successfully_when_change_book_information() throws Exception {
        BookEntity expectedBook = new BookEntity("001", "book1", "author2", 11.11);
        BookEntity oldBook = new BookEntity("001", "book1", "author1", 11.11);
        bookShelfController.update(oldBook.getIsbn(), expectedBook);
        when(bookRepository.findOne("001")).thenReturn(expectedBook);
        BookEntity updateBook = bookService.findByIsbn("001");
        assertThat(expectedBook, is(updateBook));

    }

    @Test
    public void should_delete_book_successfully_when_delete_book() throws Exception {
        BookEntity book1 = new BookEntity("001", "book1", "author1", 11.11);
        BookEntity book2 = new BookEntity("002", "book2", "author2", 22.22);
        BookEntity book3 = new BookEntity("003", "book3", "author3", 33.33);
        Iterable<BookEntity> expectedBook = asList(book1, book2);
        bookRepository.save(asList(book1, book2, book3));
        bookService.delete(book3.getIsbn());
        when(bookRepository.findAll()).thenReturn(expectedBook);
        Iterable<BookEntity> books = bookService.findAll();
        assertThat(expectedBook, is(books));


    }
}