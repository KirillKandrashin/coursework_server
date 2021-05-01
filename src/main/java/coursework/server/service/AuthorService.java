package coursework.server.service;


import coursework.server.entity.Author;
import coursework.server.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void create(Author author){
        authorRepository.saveAndFlush(author);
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author find(Long id){
        return authorRepository.getOne(id);
    }

}
