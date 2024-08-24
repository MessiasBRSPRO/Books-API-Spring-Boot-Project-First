package com.projeto.APIBooks.DataTransferObjects;

import com.projeto.APIBooks.Entities.Book;
import com.projeto.APIBooks.Entities.GenreBook;

public record DTOInfosBook(String bookName,
                           String author,
                           double price,
                           int pagesNumber,
                           GenreBook genreBook) {

    public DTOInfosBook(Book book){
        this(book.getBookName(), book.getAuthor(),book.getPrice(), book.getPagesNumber(),  book.getGenreBook());
    }
}
