package bg.project.letscook.service;

import bg.project.letscook.model.dto.AddRecipeDTO;
import bg.project.letscook.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    private final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);

    public RecipeService(RecipeRepository recipeRepository) {
    }

    public void addRecipe(AddRecipeDTO addRecipeDTO, UserDetails userDetails) {
    }
}
