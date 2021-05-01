package coursework.server.controller;

import coursework.server.entity.Section;
import coursework.server.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SectionController {
    private SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService){
        this.sectionService = sectionService;
    }

    @PostMapping(value = "/sections")
    public ResponseEntity<?> create(@RequestBody Section section){
        sectionService.create(section);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/sections")
    public ResponseEntity<List<Section>> findAll(){
        final List<Section> sectionList = sectionService.findAll();

        return sectionList != null && !sectionList.isEmpty()
                ? new ResponseEntity<>(sectionList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/sections/{id}")
    public ResponseEntity<Section> find(@PathVariable(name="id") Long id){
        final Section section = sectionService.find(id);

        return section != null
                ? new ResponseEntity<>(section, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
