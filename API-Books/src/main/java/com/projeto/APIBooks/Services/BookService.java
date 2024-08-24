package com.projeto.APIBooks.Services;

import com.projeto.APIBooks.DataTransferObjects.DTOInfosBook;
import com.projeto.APIBooks.DataTransferObjects.DTOListedBook;
import com.projeto.APIBooks.DataTransferObjects.DTOUpdatedBook;
import com.projeto.APIBooks.Entities.Book;
import com.projeto.APIBooks.Entities.GenreBook;
import com.projeto.APIBooks.Exceptions.ExistentBookException;
import com.projeto.APIBooks.Repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/*Aqui ficam as funcionalidades de livros!
* podem ser feitas consultas no banco de dados,
* consultas com criterios, criação de livro, remoção de livro
* e atualizar dados de livros :)*/
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book registerBook(@Valid Book book){
        //Essa operação, precisa que o usuario esteja autenticado e logado no sistema!
        if(bookRepository.existsBybookName(book.getBookName())){
            throw new ExistentBookException("the book "+book.getBookName() + " of author "+book.getAuthor() + " already exists in DataBase");
        }
        return bookRepository.save(book);
    }

    public List<DTOListedBook> allBooks(){
        return bookRepository.findAll().stream().map(DTOListedBook::new).toList();
    }

    public void deleteBookById(long id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
        }else{
            throw new NoSuchElementException("Book don't exists");
        }
    }

    public DTOInfosBook searchBookById(long id){
        Book book = bookRepository.getReferenceById(id);
        if(!(bookRepository.existsById(id))){
            throw new NoSuchElementException("Book don't exists");
        }
        return new DTOInfosBook(book);
    }
    public DTOInfosBook searchBookBybookName(String bookName){
        Book book = bookRepository.getReferenceBybookName(bookName);
        return new DTOInfosBook(book);
    }

    public DTOUpdatedBook updateBook(DTOUpdatedBook newBookModel){
       Book book = bookRepository.getReferenceById(newBookModel.id());
       book.updateBook(newBookModel);
       return new DTOUpdatedBook(book);
    }

    public List<DTOListedBook> allBooksByAuthorName(String authorName) {
        List<DTOListedBook> allBooksOfThisAuthor = new ArrayList<>();
        for (DTOListedBook book : allBooks()){
            if(book.author().equals(authorName)){
                allBooksOfThisAuthor.add(book);
            }
        }
        return allBooksOfThisAuthor;
    }

    public List<DTOListedBook> allBooksByPrice(double price) {
        List<DTOListedBook> allBooksOfThisPrice = new ArrayList<>();
        for (DTOListedBook book : allBooks()){
            if(book.price() <= price){
                allBooksOfThisPrice.add(book);
            }
        }
        return allBooksOfThisPrice;
    }

    public List<DTOListedBook> allBooksByGenre(GenreBook genreBook){
        List<DTOListedBook> allBooksOfThisGenre = new ArrayList<>();
        for (DTOListedBook book : allBooks()){
            if(book.genreBook() == genreBook){
                allBooksOfThisGenre.add(book);
            }
        }
        return allBooksOfThisGenre;
    }

}
