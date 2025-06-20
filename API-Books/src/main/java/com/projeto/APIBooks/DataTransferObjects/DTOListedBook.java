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
        public DTOListedBook(Book book){
                this(book.getId(), book.getBookName(), book.getAuthor(), book.getPrice(), book.getGenreBook());
        }
}
