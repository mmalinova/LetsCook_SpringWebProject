package bg.project.letscook.repository;

import bg.project.letscook.model.entity.ImageEntity;
import bg.project.letscook.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    Optional<List<ImageEntity>> findAllByRecipeId(RecipeEntity recipe);
}
