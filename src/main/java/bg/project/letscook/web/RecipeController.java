package bg.project.letscook.web;

import bg.project.letscook.model.dto.AddRecipeDTO;
import bg.project.letscook.repository.RecipeRepository;
import bg.project.letscook.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("recipe")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeService recipeService, RecipeRepository recipeRepository) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipe_add")
    public String login() {
        return "recipe_add";
    }

    @ModelAttribute("recipeModel")
    public AddRecipeDTO initRecipeModel() {
        return new AddRecipeDTO();
    }

    @PostMapping("/recipe_add")
    public String addRecipe(@Valid AddRecipeDTO addRecipeDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeModel", addRecipeDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeModel", bindingResult);

            return "redirect:/recipe/recipe_add";
        }
        //recipeRepository.save(addRecipeDTO);
        return "redirect:/";
    }
}
