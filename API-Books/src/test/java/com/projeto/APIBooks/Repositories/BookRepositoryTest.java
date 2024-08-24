package com.projeto.APIBooks.Repositories;

import com.projeto.APIBooks.DataTransferObjects.DTOBookToTests;
import com.projeto.APIBooks.Entities.Book;
import com.projeto.APIBooks.Entities.GenreBook;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")

/*In this class we Learned How Write Tests in Java using JUnit5
*
* The type of Test than make, are the Unit Tests, than are tests more specific feitos in App,
* ou seja, we tested A One class, a Method...*/
class BookRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName("Should get Book successfully from db") // O titulo do teste, a causa do teste
    void existsBybookName() {
        DTOBookToTests book = new DTOBookToTests("Como fazer amigos e influenciar pessoas?", "Dale Carneghie", 36.99, 272, GenreBook.COURSE);
        this.createBookObj(book);

        Book book1 = this.repository.getReferenceBybookName(book.bookName());

        assertThat(book1);
    }

    @Test
    @DisplayName("if the Book don't exists in db")
    void existsBybookNameCase2() {
        //DTOBookToTests book = new DTOBookToTests("Como fazer amigos e influenciar pessoas?", "Dale Carneghie", 36.99, 272, GenreBook.COURSE);
        String bookName = "Como fazer amigos";
        Book book1 = this.repository.getReferenceBybookName(bookName);

        assertThat(book1);
    }

    private Book createBookObj(DTOBookToTests book){
        Book bookObj = new Book(book);
        entityManager.persist(bookObj);
        return bookObj;
    }
}