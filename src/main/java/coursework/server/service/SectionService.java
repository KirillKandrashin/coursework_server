package coursework.server.service;


import coursework.server.entity.Section;
import coursework.server.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    public void create(Section section){
        sectionRepository.saveAndFlush(section);
    }

    public List<Section> findAll(){
        return sectionRepository.findAll();
    }

    public Section find(Long id){ return sectionRepository.getOne(id); }
}