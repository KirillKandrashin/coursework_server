package coursework.server.repository;

import coursework.server.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor,Long> {
}
