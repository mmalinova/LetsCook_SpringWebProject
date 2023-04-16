package bg.project.letscook.service;

import bg.project.letscook.model.dto.recipe.CreateRecipeDTO;
import bg.project.letscook.model.dto.recipe.RecipeDetailDTO;
import bg.project.letscook.model.dto.recipe.SearchRecipeDTO;
import bg.project.letscook.model.entity.CategoryEntity;
import bg.project.letscook.model.entity.ImageEntity;
import bg.project.letscook.model.entity.RecipeEntity;
import bg.project.letscook.model.entity.UserEntity;
import bg.project.letscook.model.enums.CategoryEnum;
import bg.project.letscook.model.enums.RoleEnum;
import bg.project.letscook.model.enums.SubcategoryEnum;
import bg.project.letscook.model.mapper.RecipeMapper;
import bg.project.letscook.repository.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final RecipeMapper recipeMapper;
    private final CategoryEntity categoryEntity = new CategoryEntity();

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, ImageRepository imageRepository, CategoryRepository categoryRepository, CommentRepository commentRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
        this.recipeMapper = recipeMapper;
    }

    public boolean isOwner(String userName, Long recipeId) {
        boolean isOwner = recipeRepository.
                findById(recipeId).
                filter(o -> o.getOwner().getEmail().equals(userName)).
                isPresent();

        if (isOwner) {
            return true;
        }

        return userRepository.
                findByEmail(userName).
                filter(this::isAdmin).
                isPresent();
    }

    private boolean isAdmin(UserEntity user) {
        return user.getUserRoles().
                stream().
                anyMatch(r -> r.getRole() == RoleEnum.ADMIN);
    }

    public Set<RecipeDetailDTO> getAllRecipes() {
        return recipeRepository.
                findAll().
                stream().
                map(recipeMapper::recipeEntityToRecipeDetailDTO).collect(Collectors.toSet());
    }

    public Page<RecipeDetailDTO> getAllRecipesPageable(Pageable pageable) {
        return recipeRepository.
                findAll(pageable).
                map(recipeMapper::recipeEntityToRecipeDetailDTO);

    }

    @Cacheable("recipeByCategory")
    public Set<RecipeDetailDTO> getRecipesByCategory(String category) {
        CategoryEntity categoryEntity = categoryRepository.findByCategory(CategoryEnum.valueOf(category)).orElseThrow();
        return recipeRepository.
                findAllByCategory(categoryEntity).
                stream().
                map(recipeMapper::recipeEntityToRecipeDetailDTO).collect(Collectors.toSet());
    }

    @Cacheable("recipeBySubcategory")
    public Set<RecipeDetailDTO> getRecipesBySubcategory(String subcategory) {
        return recipeRepository.
                findAllBySubcategory(SubcategoryEnum.valueOf(subcategory)).
                stream().
                map(recipeMapper::recipeEntityToRecipeDetailDTO).collect(Collectors.toSet());
    }

    @Cacheable("recipeByVegetarian")
    public Set<RecipeDetailDTO> getRecipesByVegetarian(boolean isVegetarian) {
        CategoryEntity categoryEntity = categoryRepository.findByCategory(CategoryEnum.ВЕЧЕРЯ).orElseThrow();
        return recipeRepository.
                findAllByVegetarianAndCategory(isVegetarian, categoryEntity).
                stream().
                map(recipeMapper::recipeEntityToRecipeDetailDTO).collect(Collectors.toSet());
    }

    @Cacheable("recipeById")
    public Optional<RecipeDetailDTO> findRecipeById(Long recipeId) {
        return recipeRepository.
                findById(recipeId).
                map(recipeMapper::recipeEntityToRecipeDetailDTO);
    }

    public void addRecipe(CreateRecipeDTO addRecipeDTO, UserDetails userDetails) {
        long millis=System.currentTimeMillis();
        Date date = new Date(millis);
        ImageEntity image = new ImageEntity();

        RecipeEntity newRecipe = recipeMapper.
                createRecipeDTOToRecipeEntity(addRecipeDTO);

        UserEntity owner = userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow();

        categoryEntity.setCategory(addRecipeDTO.getCategory());
        newRecipe.setCategory(categoryEntity);
        newRecipe.setCreatedOn(date);
        newRecipe.setApproved(true);
        newRecipe.setOwner(owner);

        RecipeEntity r = recipeRepository.save(newRecipe);
        if (!addRecipeDTO.getFirstImage().isEmpty()) {
            image.setImageURL(addRecipeDTO.getFirstImage());
            image.setRecipeId(r);
            imageRepository.save(image);
        }
        if (!addRecipeDTO.getSecondImage().isEmpty()) {
            image.setImageURL(addRecipeDTO.getSecondImage());
            image.setRecipeId(r);
            imageRepository.save(image);
        }
        if (!addRecipeDTO.getThirdImage().isEmpty()) {
            image.setImageURL(addRecipeDTO.getThirdImage());
            image.setRecipeId(r);
            imageRepository.save(image);
        }
    }

    public List<RecipeDetailDTO> searchRecipe(SearchRecipeDTO searchRecipeDTO) {
        return this.recipeRepository.findAll(new RecipeSpecification(searchRecipeDTO)).
                stream().map(recipeMapper::recipeEntityToRecipeDetailDTO).
                toList();
    }

    @CacheEvict(cacheNames = "recipes", allEntries = true)
    public void refresh() {
    }
}
