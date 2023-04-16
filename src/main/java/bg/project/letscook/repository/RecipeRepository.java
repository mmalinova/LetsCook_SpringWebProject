package bg.project.letscook.repository;

import bg.project.letscook.model.entity.CategoryEntity;
import bg.project.letscook.model.entity.RecipeEntity;
import bg.project.letscook.model.enums.SubcategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long>, JpaSpecificationExecutor<RecipeEntity> {

    Set<RecipeEntity> findAllBySubcategory(SubcategoryEnum subcategory);

    Set<RecipeEntity> findAllByCategory(CategoryEntity category);

    Set<RecipeEntity> findAllByVegetarianAndCategory(boolean vegetarian, CategoryEntity category);
}
