package bg.project.letscook.service;

import bg.project.letscook.model.dto.AddRecipeDTO;
import bg.project.letscook.repository.RecipeRepository;
import bg.project.letscook.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    private final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);
    private final RecipeRepository recipeRepository;
    private final CurrentUser currentUser;

    public RecipeService(RecipeRepository recipeRepository, CurrentUser currentUser) {
        this.recipeRepository = recipeRepository;
        this.currentUser = currentUser;
    }

    public void addRecipe(AddRecipeDTO addRecipeDTO) {

    }
}
