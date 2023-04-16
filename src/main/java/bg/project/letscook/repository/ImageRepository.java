package bg.project.letscook.repository;

import bg.project.letscook.model.entity.ImageEntity;
import bg.project.letscook.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    Set<ImageEntity> findAllByRecipe(RecipeEntity recipe);
}
