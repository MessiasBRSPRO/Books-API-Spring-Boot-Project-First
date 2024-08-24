package com.projeto.APIBooks.DataTransferObjects;

import com.projeto.APIBooks.Entities.Book;
import jakarta.validation.constraints.NotNull;

public record DTOUpdatedBook(
        @NotNull
        Long id,
        String name,
        Double price) {
    public DTOUpdatedBook(Book book){
        this(book.getId(), book.getBookName(), book.getPrice());
    }
}
