package bg.project.letscook.model.entity;

import bg.project.letscook.model.enums.CategoryEnum;
import bg.project.letscook.model.enums.SubcategoryEnum;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.*;

@Entity
@Table(name = "recipe")
public class RecipeEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubcategoryEnum subcategory;
    @Column(nullable = false)
    private boolean vegetarian;
    @Column(nullable = false)
    @Positive
    private int portions;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String steps;
    @Column(nullable = false)
    @Positive
    private int hours;
    @Column(nullable = false)
    @Positive
    private int minutes;
    @Column(name = "created_on", nullable = false)
    private Date createdOn;
    @Column(nullable = false)
    private boolean approved;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity ownerId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> category = new HashSet<>();
    @OneToMany(mappedBy = "recipeId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ImageEntity> images = new HashSet<>();
    @OneToMany(mappedBy = "recipeId", cascade = CascadeType.ALL)
    private Set<CommentEntity> comments = new HashSet<>();
    @OneToMany(mappedBy = "recipeId", cascade = CascadeType.ALL)
    private Set<IngredientEntity> ingredients = new HashSet<>();
    @ManyToMany(mappedBy = "viewedRecipes")
    Set<UserEntity> views = new HashSet<>();
    @ManyToMany(mappedBy = "likedRecipes")
    Set<UserEntity> likes = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubcategoryEnum getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubcategoryEnum subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public UserEntity getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UserEntity ownerId) {
        this.ownerId = ownerId;
    }

    public Set<CategoryEntity> getCategory() {
        return category;
    }

    public void setCategory(Set<CategoryEntity> category) {
        this.category = category;
    }

    public Set<ImageEntity> getImages() {
        return images;
    }

    public void setImages(Set<ImageEntity> images) {
        this.images = images;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public Set<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<UserEntity> getViews() {
        return views;
    }

    public void setViews(Set<UserEntity> views) {
        this.views = views;
    }

    public Set<UserEntity> getLikes() {
        return likes;
    }

    public void setLikes(Set<UserEntity> likes) {
        this.likes = likes;
    }
}
