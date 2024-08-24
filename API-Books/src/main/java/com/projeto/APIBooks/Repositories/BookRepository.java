package com.projeto.APIBooks.Repositories;

import com.projeto.APIBooks.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

//Repositórios são uma interface que vao interagir com banco de dados
public interface BookRepository extends JpaRepository<Book, Long> {
    Book getReferenceBybookName(String bookName);

    boolean existsBybookName(String bookName);
}
