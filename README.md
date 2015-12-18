# thwo-bookshelf

## Workshop

### Step 0:

GET: hello {name}! 

via [Spring Web MVC DispatcherServlet](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-servlet).

### Step 1:

// given

GET: show all books in index page.

// when

// then

implement `fetch book details` functionality.

### Step 2:

// given

a ready-to-wear `form` for book information.

// when

// then

implement `create new book` functionality.

### Step 3:

- option 1: Edit / Delete / ...
- option 2: return data with JSON format => split front-end & back-end


## Extendibility

- What's Spring IoC & DI?
- Service interface => different implementation
- Data Repository



## Database Level
#### MySQL Service Started 
  - Mac OS: sudo mysql.service start 
  - Ubuntu : sudo service mysql start 
  - mysql -uroot, then run create_db.sql

#### Book Shelf Step 1
  - Continue working on project “thwo-bookshelf” Step4
  - Change BookRepository (extends CrudRepository)
  - Create Table WO_BOOK(ISBN, NAME, AUTHOR, PRICE)
  - No need to write SQL statement in this part

#### Book Shelf Step 2
  * As a DEV/DBA  I want to follow up database evolution versions  So that I can get DB maintenance in control easily
  * Example:  
  - V1: create the table BOOK 
  - V2: change BOOK’s column NAME -> TITLE 
  - V3: add unique key to (TITLE, AUTHOR) 
  - …
  * Tips: Similar with VCS

#### Book Shelf Step 3
  * As an admin  I want to add or amend the books  So that other users can get those books
  * As a general user  I want to search books by title fuzzily  So that I can get the books in the same topic
  * As a general user  I want to search books by category name  So that I can get the books I am interested in

#### Book Shelf Step 4
  * As a general user  I want to search books by price range  So that I can get the books within the price
  * As a general user  I want to put stars(1-10) and comments on a book  So that I can know the book average ranking and comments
  * As a general user  I want to get a book’s average stars and ranking  So that I can know how popular the book is

#### Check Points
  - Integration Test
  - SQL Statement
  - Spring Data JPA / Hibernate
  - CrudRepository / JpaRepository
  - Flyway Migration
  - Transactional
  - Pagination Result




