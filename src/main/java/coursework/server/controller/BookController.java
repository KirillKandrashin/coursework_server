package coursework.server.controller;

import coursework.server.entity.Book;
import coursework.server.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping(value = "/books")
    public ResponseEntity<?> create(@RequestBody Book book){
        bookService.create(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> findAll(){
        final List<Book> bookList = bookService.findAll();

        return bookList != null && !bookList.isEmpty()
                ? new ResponseEntity<>(bookList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Book> find(@PathVariable(name="id") Long id){
        final Book book = bookService.find(id);

        return book != null
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/book")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") Long id){
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> edit(@RequestBody Book book) {
        bookService.edit(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}