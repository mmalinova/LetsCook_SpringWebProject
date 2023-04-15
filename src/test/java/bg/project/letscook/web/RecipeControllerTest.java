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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void testDeleteByAnonymousUser_Forbidden() throws Exception {
        mockMvc.perform(delete("/{id}", testRecipe.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection());
        //TODO: check redirection url to login w/o schema
    }

    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testDeleteByAdmin() throws Exception {
        mockMvc.perform(delete("/{id}", testRecipe.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/recipes/recipes_dashboard"));
    }

    @WithMockUser(
            username = "user@example.com",
            roles = "USER"
    )
    @Test
    void testDeleteByOwner() throws Exception {
        mockMvc.perform(delete("/{id}", testRecipe.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/recipes/recipes_dashboard"));
    }

    @WithMockUser(
            username = "user@example.com",
            roles = "USER"
    )
    @Test
    public void testDeleteNotOwned_Forbidden() throws Exception {
        mockMvc.perform(delete("/{id}", testAdminRecipe.getId()).
                        with(csrf())
                ).
                andExpect(status().isForbidden());
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
