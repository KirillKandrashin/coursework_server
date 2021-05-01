package coursework.server.controller;

import coursework.server.entity.Author;
import coursework.server.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping(value = "/authors")
    public ResponseEntity<?> create(@RequestBody Author author){
        authorService.create(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/authors")
    public ResponseEntity<List<Author>> findAll(){
        final List<Author> authorList = authorService.findAll();

        return authorList != null && !authorList.isEmpty()
                ? new ResponseEntity<>(authorList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/authors/{id}")
    public ResponseEntity<Author> find(@PathVariable(name="id") Long id){
        final Author author = authorService.find(id);

        return author != null
                ? new ResponseEntity<>(author, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

