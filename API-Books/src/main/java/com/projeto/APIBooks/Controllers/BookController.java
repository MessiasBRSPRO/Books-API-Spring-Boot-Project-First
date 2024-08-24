package com.projeto.APIBooks.Controllers;

import com.projeto.APIBooks.DataTransferObjects.DTOInfosBook;
import com.projeto.APIBooks.DataTransferObjects.DTOListedBook;
import com.projeto.APIBooks.DataTransferObjects.DTOUpdatedBook;
import com.projeto.APIBooks.Entities.Book;
import com.projeto.APIBooks.Entities.GenreBook;
import com.projeto.APIBooks.Services.BookService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


//Aqui onde a app funciona, para fazermos tal coisa na app, precisamos acessar endpoints
// que são urls que produziram um tipo de requisição ao sistema
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    @RequestMapping("/registerbook")
    @Transactional
    public ResponseEntity<Book> registerBook(@RequestBody @Valid Book book, UriComponentsBuilder uriBuilder){
        //this endpoint demand a Token to be accessed;
        URI uri = uriBuilder.path("/registerbook/id={id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).body(bookService.registerBook(book));//Created 201
    }

    @GetMapping
    @RequestMapping("/books/id={id}")
    public ResponseEntity<DTOInfosBook> searchBookById(@PathVariable long id){
        return ResponseEntity.ok().body(bookService.searchBookById(id));
    }

    @GetMapping
    @RequestMapping("/books/bookName={name}")
    public ResponseEntity<DTOInfosBook> searchBookByName(@PathVariable String name){
        return ResponseEntity.ok().body(bookService.searchBookBybookName(name));
    }

    @GetMapping
    @RequestMapping("/books")
    public ResponseEntity<List<DTOListedBook>> allBooks(){
        return ResponseEntity.ok().body(bookService.allBooks());
    }


    @GetMapping
    @RequestMapping("/books/author={authorName}")
    public ResponseEntity<List<DTOListedBook>> allBooksByAuthor(@PathVariable String authorName){
        return ResponseEntity.ok().body(bookService.allBooksByAuthorName(authorName));
    }

    @GetMapping
    @RequestMapping("/books/price={price}")
    public ResponseEntity<List<DTOListedBook>> allBooksByPrice(@PathVariable double price){
        return ResponseEntity.ok().body(bookService.allBooksByPrice(price));
    }

    @GetMapping
    @RequestMapping("/books/genreBook={genreBook}")
    public ResponseEntity<List<DTOListedBook>> allBooksByGenreBook(@PathVariable GenreBook genreBook){
        return ResponseEntity.ok().body(bookService.allBooksByGenre(genreBook));
    }

    @PutMapping
    @RequestMapping("/updatebook")
    @Transactional // Annotation used for SQL operations than will modify any data existent in db
    public ResponseEntity<DTOUpdatedBook> updateBook(@RequestBody @Valid DTOUpdatedBook newModel){
        //this endpoint demand a Token to be accessed;
        return ResponseEntity.ok().body(bookService.updateBook(newModel));
    }

    @DeleteMapping
    @RequestMapping("/deletebook/id={id}")
    @Transactional
    public ResponseEntity<?> deleteBookEndpoint(@PathVariable long id){
        //this endpoint demand a Token to be accessed;
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
