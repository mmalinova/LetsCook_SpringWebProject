package bg.project.letscook.util;

import bg.project.letscook.model.entity.*;
import bg.project.letscook.model.enums.CategoryEnum;
import bg.project.letscook.model.enums.RoleEnum;
import bg.project.letscook.model.enums.SubcategoryEnum;
import bg.project.letscook.repository.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestDataUtils {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private ImageRepository imageRepository;
    private CommentRepository commentRepository;
    private long millis = System.currentTimeMillis();
    private Date date = new Date(millis);

    public TestDataUtils(UserRepository userRepository, UserRoleRepository userRoleRepository, RecipeRepository recipeRepository, CategoryRepository categoryRepository, ImageRepository imageRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
        this.commentRepository = commentRepository;
    }

    private void initRoles() {
        RoleEntity adminRole = new RoleEntity().setRole(RoleEnum.ADMIN);
        RoleEntity userRole = new RoleEntity().setRole(RoleEnum.USER);

        userRoleRepository.save(adminRole);
        userRoleRepository.save(userRole);
    }

    public UserEntity createTestAdmin(String email) {

        initRoles();

        var admin = new UserEntity().
                setEmail(email).
                setFirstName("Admin").
                setLastName("Adminov").
                setActive(true).
                setUserRoles(new HashSet<>(userRoleRepository.findAll()));

        return userRepository.save(admin);
    }

    public UserEntity createTestUser(String email) {

        initRoles();

        var user = new UserEntity().
                setEmail(email).
                setFirstName("User").
                setLastName("Userov").
                setActive(true).
                setUserRoles(new HashSet<>(userRoleRepository.
                        findAll().stream().
                        filter(r -> r.getRole() != RoleEnum.ADMIN).
                        toList()));

        return userRepository.save(user);
    }

    public RecipeEntity createTestRecipe(UserEntity owner,
                                         CategoryEntity categoryEntity) {
        var recipeEntity = new RecipeEntity().
                setApproved(true).
                setCategory(categoryEntity).
                setCreatedOn(date).
                setImages(Set.of()).
                setHours(2).
                setMinutes(3).
                setComments(Set.of()).
                setIngredients("банан, ягоди, круши").
                setLikes(Set.of()).
                setName("Плодова салата").
                setPortions(1).
                setSteps("обелваш, нарязваш и ядеш").
                setVegetarian(true).
                setSubcategory(SubcategoryEnum.САЛАТА).
                setOwner(owner);

        return recipeRepository.save(recipeEntity);
    }

    public CategoryEntity createTestCategory() {
        CategoryEntity categoryEntity = new CategoryEntity().
                setCategory(CategoryEnum.ДЕСЕРТ);

        return categoryRepository.save(categoryEntity);
    }

    public ImageEntity createTestImage(RecipeEntity recipeEntity) {
        ImageEntity image = new ImageEntity().
                setImageURL("https://www.google.com/search?q=recipe&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjl8ab_wKv-AhUIQ_EDHZnLBwMQ_AUoAnoECAEQBA&biw=1455&bih=679&dpr=1.32#imgrc=szRpvsGF2Q_DAM").
                setRecipeId(recipeEntity);
        return imageRepository.save(image);
    }

    public CommentEntity createTestComment(UserEntity owner,
                                           RecipeEntity recipeEntity) {
        CommentEntity comment = new CommentEntity().
                setApproved(true).
                setCreatedOn(date).
                setOwnerId(owner).
                setText("asd").setRecipeId(recipeEntity);
        return commentRepository.save(comment);
    }

    public void cleanUpDatabase() {
        recipeRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        imageRepository.deleteAll();
        categoryRepository.deleteAll();
    }
}
