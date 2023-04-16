package bg.project.letscook.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_likes_recipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    Set<RecipeEntity> likedRecipes = new HashSet<>();
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Моля, въведи Email")
    @Email(message = "Невалиден Email")
    private String email;
    private String password;
    @Column(name = "image_url")
    private String imageURL;
    @Column(nullable = false)
    private boolean active;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RecipeEntity> myRecipes = new HashSet<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CommentEntity> myComments = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> userRoles = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public UserEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public UserEntity setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Set<RecipeEntity> getMyRecipes() {
        return myRecipes;
    }

    public UserEntity setMyRecipes(Set<RecipeEntity> myRecipes) {
        this.myRecipes = myRecipes;
        return this;
    }

    public Set<CommentEntity> getMyComments() {
        return myComments;
    }

    public UserEntity setMyComments(Set<CommentEntity> myComments) {
        this.myComments = myComments;
        return this;
    }

    public Set<RecipeEntity> getLikedRecipes() {
        return likedRecipes;
    }

    public UserEntity setLikedRecipes(Set<RecipeEntity> likedRecipes) {
        this.likedRecipes = likedRecipes;
        return this;
    }

    public Set<RoleEntity> getUserRoles() {
        return userRoles;
    }

    public UserEntity setUserRoles(Set<RoleEntity> userRoles) {
        this.userRoles = userRoles;
        return this;
    }
}
