package bg.project.letscook.repository;

import bg.project.letscook.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long>, JpaSpecificationExecutor<RecipeEntity> {

    Optional<RecipeEntity> findByName(String name);
}
