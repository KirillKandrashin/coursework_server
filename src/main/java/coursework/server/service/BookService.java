package coursework.server.service;


import coursework.server.entity.Book;
import coursework.server.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public void create(Book book){
        bookRepository.saveAndFlush(book);
    } //save Просто сохранение, saveAndFlush сразу в бд

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book find(Long id){
        return bookRepository.getOne(id);
    }

    public void delete(Long id){ bookRepository.deleteById(id); }

    public Book edit(Book book) {
        return bookRepository.saveAndFlush(book);
    }
}