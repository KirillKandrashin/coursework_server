package coursework.server.controller;

import coursework.server.entity.Visitor;
import coursework.server.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class VisitorController {
    private VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService){
        this.visitorService = visitorService;
    }

    @PostMapping(value = "/visitors")
    public ResponseEntity<?> create(@RequestBody Visitor visitor){
        visitorService.create(visitor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/visitors")
    public ResponseEntity<List<Visitor>> findAll(){
        final List<Visitor> visitorList = visitorService.findAll();

        return visitorList != null && !visitorList.isEmpty()
                ? new ResponseEntity<>(visitorList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/visitors/{id}")
    public ResponseEntity<Visitor> find(@PathVariable(name="id") Long id){
        final Visitor visitor = visitorService.find(id);

        return visitor != null
                ? new ResponseEntity<>(visitor, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/visitor")
    public ResponseEntity<?> deleteById(@PathVariable(name="id") Long id){
        visitorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/visitors/{id}")
    public ResponseEntity<Visitor> edit(@RequestBody Visitor visitor) {
        visitorService.edit(visitor);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
