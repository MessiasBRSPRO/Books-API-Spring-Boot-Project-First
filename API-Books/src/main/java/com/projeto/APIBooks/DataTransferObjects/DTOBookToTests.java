package com.projeto.APIBooks.DataTransferObjects;

import com.projeto.APIBooks.Entities.GenreBook;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOBookToTests(
        @NotBlank
        String bookName,

        @NotBlank
        String author,

        @NotNull
        double price,
        @NotNull
        int pagesNumber,
        @Enumerated
        GenreBook genreBook) {

}
