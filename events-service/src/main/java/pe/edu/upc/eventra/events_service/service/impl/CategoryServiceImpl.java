package pe.edu.upc.eventra.events_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upc.eventra.events_service.model.dto.CategoryRequest;
import pe.edu.upc.eventra.events_service.model.dto.CategoryResponse;
import pe.edu.upc.eventra.events_service.model.entity.Category;
import pe.edu.upc.eventra.events_service.repository.CategoryRepository;
import pe.edu.upc.eventra.events_service.service.CategoryService;
import pe.edu.upc.eventra.sharedservice.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public void addCategoryEvent(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .category(categoryRequest.getName())
                .build();
        categoryRepository.save(category);
        log.info("Event category added: {}", category);
    }

    public List<CategoryResponse> getAllCategoryEvents() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(event -> new CategoryResponse(event.getId(), event.getCategory()))
                .collect(Collectors.toList());
    }

    public void updateCategoryEvent(long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event category not found with id: " + id));

        category.setCategory(categoryRequest.getName());
        categoryRepository.save(category);
        log.info("Updated event category: {}", category);
    }

    public void deleteCategoryEvent(long id) {
        categoryRepository.deleteById(id);
        log.info("Deleted event category with id: {}", id);
    }
}

