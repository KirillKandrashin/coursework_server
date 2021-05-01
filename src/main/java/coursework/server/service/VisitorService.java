package coursework.server.service;


import coursework.server.entity.Visitor;
import coursework.server.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {
    @Autowired
    private VisitorRepository visitorRepository;

    public void create(Visitor visitor){
        visitorRepository.saveAndFlush(visitor);
    }

    public List<Visitor> findAll(){
        return visitorRepository.findAll();
    }

    public Visitor find(Long id){ return visitorRepository.getOne(id); }

    public void delete(Long id){ visitorRepository.deleteById(id); }

    public Visitor edit(Visitor visitor) {
        return visitorRepository.saveAndFlush(visitor);
    }
}