package coursework.server.service;


import coursework.server.entity.Publisher;
import coursework.server.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public void create(Publisher publisher){
        publisherRepository.saveAndFlush(publisher);
    }

    public List<Publisher> findAll(){
        return publisherRepository.findAll();
    }

    public Publisher find(Long id){ return publisherRepository.getOne(id); }
}