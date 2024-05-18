package pe.edu.upc.eventra.events_service.service;

import pe.edu.upc.eventra.events_service.model.dto.CategoryRequest;
import pe.edu.upc.eventra.events_service.model.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    void addCategoryEvent(CategoryRequest categoryRequest);

    List<CategoryResponse> getAllCategoryEvents();

    void updateCategoryEvent(long id, CategoryRequest categoryRequest);

    void deleteCategoryEvent(long id);
}
