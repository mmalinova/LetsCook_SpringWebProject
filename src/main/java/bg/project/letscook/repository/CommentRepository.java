package bg.project.letscook.repository;

import bg.project.letscook.model.entity.CommentEntity;
import bg.project.letscook.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    Set<CommentEntity> findAllByRecipe(RecipeEntity recipe);
}
