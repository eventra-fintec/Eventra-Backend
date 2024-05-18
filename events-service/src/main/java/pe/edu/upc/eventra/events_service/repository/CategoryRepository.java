package pe.edu.upc.eventra.events_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.eventra.events_service.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Repository methods...
}

