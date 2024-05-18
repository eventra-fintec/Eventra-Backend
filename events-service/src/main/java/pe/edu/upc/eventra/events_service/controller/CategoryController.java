package pe.edu.upc.eventra.events_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.events_service.model.dto.CategoryRequest;
import pe.edu.upc.eventra.events_service.model.dto.CategoryResponse;
import pe.edu.upc.eventra.events_service.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categoryevent")
@RequiredArgsConstructor
@Tag(name = "CategoryEventController", description = "API for event category operations")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new event category", description = "Adds a new event category to the system")
    @ApiResponse(responseCode = "201", description = "Event category created")
    public void addCategoryEvent(@RequestBody CategoryRequest categoryRequest) {
        categoryService.addCategoryEvent(categoryRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all event categories", description = "Retrieves a list of all event categories")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of event categories")
    public List<CategoryResponse> getAllCategoryEvents() {
        return categoryService.getAllCategoryEvents();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update an event category", description = "Updates a specific event category by their ID")
    @ApiResponse(responseCode = "200", description = "Event category updated")
    public void updateCategoryEvent(@PathVariable("id") long id, @RequestBody CategoryRequest categoryRequest) {
        categoryService.updateCategoryEvent(id, categoryRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryEvent(@PathVariable("id") long id) {
        categoryService.deleteCategoryEvent(id);
    }
}

