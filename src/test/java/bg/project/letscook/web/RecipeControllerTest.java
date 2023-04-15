package bg.project.letscook.web;

import bg.project.letscook.model.entity.*;
import bg.project.letscook.util.TestDataUtils;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testUser, testAdmin;

    private RecipeEntity testRecipe, testAdminRecipe;

    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("user@example.com");
        testAdmin = testDataUtils.createTestAdmin("admin@example.com");
        var testCategory = testDataUtils.createTestCategory();

//        testRecipe = testDataUtils.createTestRecipe(testUser, testCategory);
//        testAdminRecipe = testDataUtils.createTestRecipe(testAdmin, testCategory);
//
//        var testImage = testDataUtils.createTestImage(testRecipe);
//        var testComment = testDataUtils.createTestComment(testUser, testRecipe);
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    void testRecipesDashboardPageShown() throws Exception {
        mockMvc.perform(get("/recipes/recipes_dashboard")).andExpect(status().isOk()).andExpect(view().name("recipes_dashboard"));
    }

    @Test
    void testRecipesAddPageShown() throws Exception {
        mockMvc.perform(get("/recipes/recipe_add")).andExpect(status().is3xxRedirection());
    }

    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserData")
    @Test
    void testAddRecipe() throws Exception {

        mockMvc.perform(post("/recipes/recipe_add").
                        param("approved", "true").
                        param("category", "CategoryEnum.ДЕСЕРТ").
                        param("images", "").
                        param("minutes", "3").
                        param("hours", "2").
                        param("comments", "").
                        param("ingredients", "банан, ягоди, круши").
                        param("likes", "").
                        param("name", "Плодова салата").
                        param("portions", "1").
                        param("steps", "обелваш, нарязваш и ядеш").
                        param("vegetarian", "true").
                        param("subcategory", "SubcategoryEnum.САЛАТА").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/recipes/recipe_add"));
    }
}
