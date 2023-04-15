package bg.project.letscook.service;

import bg.project.letscook.model.dto.category.CategoryDTO;
import bg.project.letscook.model.entity.CategoryEntity;
import bg.project.letscook.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository brandRepository) {
        this.categoryRepository = brandRepository;
    }

    public Set<CategoryDTO> getAllCategories() {
        return categoryRepository.
                findAll().
                stream().
                map(this::mapCategory).
                collect(Collectors.toSet());
    }

    private CategoryDTO mapCategory(CategoryEntity categoryEntity) {
        return new CategoryDTO().
                setCategory(categoryEntity.getCategory());

    }
}
