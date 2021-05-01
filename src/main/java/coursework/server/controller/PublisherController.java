package coursework.server.controller;

import coursework.server.entity.Publisher;
import coursework.server.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PublisherController {
    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService){
        this.publisherService = publisherService;
    }

    @PostMapping(value = "/publishers")
    public ResponseEntity<?> create(@RequestBody Publisher publisher){
        publisherService.create(publisher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/publishers")
    public ResponseEntity<List<Publisher>> findAll(){
        final List<Publisher> publisherList = publisherService.findAll();

        return publisherList != null && !publisherList.isEmpty()
                ? new ResponseEntity<>(publisherList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/publishers/{id}")
    public ResponseEntity<Publisher> find(@PathVariable(name="id") Long id){
        final Publisher publisher = publisherService.find(id);

        return publisher != null
                ? new ResponseEntity<>(publisher, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
