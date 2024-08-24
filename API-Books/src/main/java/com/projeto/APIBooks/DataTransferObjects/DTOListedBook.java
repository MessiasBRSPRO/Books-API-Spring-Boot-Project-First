package com.projeto.APIBooks.DataTransferObjects;

import com.projeto.APIBooks.Entities.Book;
import com.projeto.APIBooks.Entities.GenreBook;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOListedBook(
        long id, @NotBlank
        String bookName,

        @NotBlank
        String author,

        @NotNull
        double price,

        @Enumerated
        GenreBook genreBook) {
        public DTOListedBook(Book remedio){
                this(remedio.getId(), remedio.getBookName(), remedio.getAuthor(), remedio.getPrice(), remedio.getGenreBook());
        }
}
