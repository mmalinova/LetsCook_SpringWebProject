package bg.project.letscook.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    private String password;
    @Column(name = "image_url")
    private String imageURL;
    @Column(nullable = false)
    private boolean active;
    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL)
    private Set<RecipeEntity> myRecipes = new HashSet<>();
    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL)
    private Set<IngredientEntity> myProducts = new HashSet<>();
    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL)
    private Set<CommentEntity> myComments = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "user_views_recipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    Set<RecipeEntity> viewedRecipes = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "user_likes_recipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    Set<RecipeEntity> likedRecipes = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> userRoles = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<RecipeEntity> getMyRecipes() {
        return myRecipes;
    }

    public void setMyRecipes(Set<RecipeEntity> myRecipes) {
        this.myRecipes = myRecipes;
    }

    public Set<IngredientEntity> getMyProducts() {
        return myProducts;
    }

    public void setMyProducts(Set<IngredientEntity> myProducts) {
        this.myProducts = myProducts;
    }

    public Set<CommentEntity> getMyComments() {
        return myComments;
    }

    public void setMyComments(Set<CommentEntity> myComments) {
        this.myComments = myComments;
    }

    public Set<RecipeEntity> getViewedRecipes() {
        return viewedRecipes;
    }

    public void setViewedRecipes(Set<RecipeEntity> viewedRecipes) {
        this.viewedRecipes = viewedRecipes;
    }

    public Set<RecipeEntity> getLikedRecipes() {
        return likedRecipes;
    }

    public void setLikedRecipes(Set<RecipeEntity> likedRecipes) {
        this.likedRecipes = likedRecipes;
    }

    public Set<RoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<RoleEntity> userRoles) {
        this.userRoles = userRoles;
    }
}
