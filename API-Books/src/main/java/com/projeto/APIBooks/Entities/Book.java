package com.projeto.APIBooks.Entities;

import com.projeto.APIBooks.DataTransferObjects.DTOBookToTests;
import com.projeto.APIBooks.DataTransferObjects.DTOListedBook;
import com.projeto.APIBooks.DataTransferObjects.DTOUpdatedBook;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"bookName", "author"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String bookName;

    @NotBlank
    private String author;

    @NotNull
    private double price;

    @NotNull
    private int pagesNumber;

    @Enumerated
    private GenreBook genreBook;


    public Book(DTOListedBook dtoListedBook){
        this.bookName = dtoListedBook.bookName();
        this.author = dtoListedBook.author();
        this.price = dtoListedBook.price();
    }

    public Book(DTOBookToTests bookDTO){
        this.bookName = bookDTO.bookName();
        this.author = bookDTO.author();
        this.price = bookDTO.price();
        this.pagesNumber = bookDTO.pagesNumber();
        this.genreBook = bookDTO.genreBook();
    }

    public void updateBook(@Valid DTOUpdatedBook newModel){
        if(newModel.name() != null){
            this.bookName = newModel.name();
        }if(newModel.price() != null){
            this.price = newModel.price();
        }if(newModel.id() != null){
            this.id = newModel.id();
        }
    }
}
