package bg.project.letscook.model.mapper;

import bg.project.letscook.model.dto.recipe.CreateOrUpdateRecipeDTO;
import bg.project.letscook.model.dto.recipe.RecipeDetailDTO;
import bg.project.letscook.model.entity.CategoryEntity;
import bg.project.letscook.model.entity.ImageEntity;
import bg.project.letscook.model.entity.RecipeEntity;
import bg.project.letscook.model.enums.CategoryEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    @Named("categoriesSet")
    static Set<String> mapCategories(Set<CategoryEntity> categories) {
        return categories.stream().map(category -> category.getCategory().name()).collect(Collectors.toSet());
    }

    @Named("imagesSet")
    static Set<String> mapImages(Set<ImageEntity> images) {
        return images.stream().map(ImageEntity::getImageURL).collect(Collectors.toSet());
    }

    @Named("catMap")
    static Set<CategoryEntity> catMap(CategoryEnum category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategory(category);
        return Set.of(categoryEntity);
    }

    @Named("categoryMap")
    static CategoryEnum categoryMap(Set<CategoryEntity> categoryEntities) {
        return categoryEntities.stream().map(CategoryEntity::getCategory).findFirst().orElseThrow();
    }

    @Mapping(source = "category", target = "category", qualifiedByName = "catMap")
    RecipeEntity createOrUpdateRecipeDTOToRecipeEntity(CreateOrUpdateRecipeDTO createOrUpdateRecipeDTO);

    @Mapping(source = "category", target = "category", qualifiedByName = "categoryMap")
    CreateOrUpdateRecipeDTO recipeEntityToCreateOrUpdateRecipeDTO(RecipeEntity recipeEntity);

    @Mapping(source = "category", target = "categories", qualifiedByName = "categoriesSet")
    @Mapping(source = "images", target = "images", qualifiedByName = "imagesSet")
    @Mapping(source = "owner.firstName", target = "ownerFirstName")
    @Mapping(source = "owner.lastName", target = "ownerLastName")
    RecipeDetailDTO recipeEntityToRecipeDetailDTO(RecipeEntity recipeEntity);
}
