package bg.project.letscook.repository;

import bg.project.letscook.model.entity.CategoryEntity;
import bg.project.letscook.model.entity.CommentEntity;
import bg.project.letscook.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
