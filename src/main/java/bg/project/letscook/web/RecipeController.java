package bg.project.letscook.web;

import bg.project.letscook.exception.ObjectNotFoundException;
import bg.project.letscook.model.dto.recipe.CreateOrUpdateRecipeDTO;
import bg.project.letscook.model.dto.recipe.RecipeDetailDTO;
import bg.project.letscook.service.CategoryService;
import bg.project.letscook.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/recipes_dashboard")
    public String getAllRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "recipes_dashboard";
    }

    @GetMapping("/recipe_add")
    public String addRecipe(Model model) {
        if (!model.containsAttribute("addRecipeModel")) {
            model.addAttribute("addRecipeModel", new CreateOrUpdateRecipeDTO());
        }
        model.addAttribute("category", categoryService.getAllCategories());
        return "recipe_add";
    }

    @PostMapping("/recipe_add")
    public String addRecipe(@Valid CreateOrUpdateRecipeDTO addRecipeDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addRecipeModel", addRecipeDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addRecipeModel", bindingResult);

            return "redirect:/recipes/recipe_add";
        }
        recipeService.addRecipe(addRecipeDTO, userDetails);
        return "redirect:/recipes/recipes_dashboard";
    }

    @GetMapping("/evict")
    public ResponseEntity<RecipeDetailDTO> evict() {
        recipeService.refresh();
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("/{id}")
    public String deleteOffer(
            @PathVariable("id") Long id) {
        recipeService.deleteOfferById(id);

        return "redirect:/recipes/recipes_dashboard";
    }

    @GetMapping("/recipes_dashboard/{id}")
    public String getOfferDetail(@PathVariable("id") Long id,
                                 Model model) {

        var recipeDto =
                recipeService.findRecipeById(id).
                        orElseThrow(() -> new ObjectNotFoundException("Recipe with ID " +
                                id + " not found!"));

        model.addAttribute("recipe", recipeDto);

        return "recipe_details";
    }
}
