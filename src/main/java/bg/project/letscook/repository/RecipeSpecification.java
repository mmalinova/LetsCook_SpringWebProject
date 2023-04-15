package bg.project.letscook.repository;

import bg.project.letscook.model.dto.recipe.SearchRecipeDTO;
import bg.project.letscook.model.entity.RecipeEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RecipeSpecification implements Specification<RecipeEntity> {

    private final SearchRecipeDTO searchRecipeDTO;

    public RecipeSpecification(SearchRecipeDTO searchRecipeDTO) {
        this.searchRecipeDTO = searchRecipeDTO;
    }

    @Override
    public Predicate toPredicate(Root<RecipeEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate p = criteriaBuilder.conjunction();

        if (searchRecipeDTO.getProductName() != null && !searchRecipeDTO.getProductName().isEmpty()) {
            p.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder.like(root.get("ingredients"), searchRecipeDTO.getProductName()))
            );
        }

        if (searchRecipeDTO.getCategory() != null&& !searchRecipeDTO.getCategory().isEmpty()) {
            p.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder.equal(root.join("category").get("category"), searchRecipeDTO.getCategory()))
            );
        }

        if (searchRecipeDTO.getPortions() != null) {
            p.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder.equal(root.get("portions"), searchRecipeDTO.getPortions()))
            );
        }

        return p;
    }
}
