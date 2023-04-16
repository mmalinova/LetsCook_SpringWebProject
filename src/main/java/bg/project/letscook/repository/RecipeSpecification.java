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

        if (searchRecipeDTO.getName() != null && !searchRecipeDTO.getName().isEmpty()) {
            p.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder.like(root.get("name"), searchRecipeDTO.getName()))
            );
        }

        if (searchRecipeDTO.getPortions() != null) {
            p.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("portions"), searchRecipeDTO.getPortions()))
            );
        }

        if (searchRecipeDTO.getSubcategory() != null) {
            p.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder.equal(root.get("subcategory"), searchRecipeDTO.getSubcategory()))
            );
        }

        if (searchRecipeDTO.getCategory() != null) {
            p.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder.equal(root.get("category").get("category"), searchRecipeDTO.getCategory()))
            );
        }

        return p;
    }
}
