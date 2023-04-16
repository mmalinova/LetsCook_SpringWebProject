package bg.project.letscook.web;

import bg.project.letscook.exception.ObjectNotFoundException;
import bg.project.letscook.model.dto.recipe.CreateRecipeDTO;
import bg.project.letscook.model.dto.recipe.RecipeDetailDTO;
import bg.project.letscook.model.dto.recipe.SearchRecipeDTO;
import bg.project.letscook.model.enums.SubcategoryEnum;
import bg.project.letscook.service.CategoryService;
import bg.project.letscook.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    @GetMapping("/recipes_dashboard/category/{category}")
    public String getAllRecipesByCategory(@PathVariable("category") String category,
                                             Model model) {
        model.addAttribute("recipes", recipeService.getRecipesByCategory(category));
        return "recipes_dashboard";
    }

    @GetMapping("/recipes_dashboard/vegetarian/{vegetarian}")
    public String getAllDinnerRecipesByVegetarian(@PathVariable("vegetarian") int isVegetarian,
                                             Model model) {
        model.addAttribute("recipes", recipeService.getRecipesByVegetarian(isVegetarian > 0));
        return "recipes_dashboard";
    }

    @GetMapping("/recipes_dashboard/subcategory/{subcategory}")
    public String getAllRecipesBySubcategory(@PathVariable("subcategory") String subcategory,
                                             Model model) {
        model.addAttribute("recipes", recipeService.getRecipesBySubcategory(subcategory));
        return "recipes_dashboard";
    }

    @GetMapping("/recipe_add")
    public String addRecipe(Model model) {
        if (!model.containsAttribute("addRecipeModel")) {
            model.addAttribute("addRecipeModel", new CreateRecipeDTO());
        }
        model.addAttribute("category", categoryService.getAllCategories());
        return "recipe_add";
    }

    @PostMapping("/recipe_add")
    public String addRecipe(@Valid CreateRecipeDTO addRecipeDTO,
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

    @GetMapping("/recipes_dashboard/{id}")
    public String getRecipeDetail(@PathVariable("id") Long id,
                                 Model model) {

        var recipeDto =
                recipeService.findRecipeById(id).
                        orElseThrow(() -> new ObjectNotFoundException("Recipe with ID " +
                                id + " not found!"));

        if (recipeDto.isVegetarian()) recipeDto.setVegetarianString("Да");
        else recipeDto.setVegetarianString("Не");

        recipeDto.setSplitSteps(Arrays.stream(recipeDto.getSteps().split("\\r?\\n")).collect(Collectors.toSet()));

        model.addAttribute("recipe", recipeDto);

        return "recipe_details";
    }

    @GetMapping("/recipe_search")
    public String searchQuery(@Valid SearchRecipeDTO searchRecipeDTO,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchRecipe", searchRecipeDTO);
            model.addAttribute(
                    "org.springframework.validation.BindingResult.searchRecipe",
                    bindingResult);
            return "recipe_search";
        }

        if (!model.containsAttribute("searchRecipe")) {
            model.addAttribute("searchRecipe", searchRecipeDTO);
        }

        if (!searchRecipeDTO.isEmpty()) {
            model.addAttribute("recipes", recipeService.searchRecipe(searchRecipeDTO));
        }

        return "recipe_search";
    }
}
