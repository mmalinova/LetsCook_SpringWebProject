package bg.project.letscook.model.entity;

import bg.project.letscook.model.enums.SubcategoryEnum;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private int minutes;
    @Column(name = "created_on", nullable = false)
    private Date createdOn;
    @Column(nullable = false)
    private boolean approved;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String ingredients;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;
    @ManyToMany(mappedBy = "likedRecipes")
    private Set<UserEntity> likes = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    @OneToMany(mappedBy = "recipeId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ImageEntity> images = new HashSet<>();
    @OneToMany(mappedBy = "recipeId", cascade = CascadeType.ALL)
    private Set<CommentEntity> comments = new HashSet<>();

    public String getName() {
        return name;
    }

    public RecipeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public SubcategoryEnum getSubcategory() {
        return subcategory;
    }

    public RecipeEntity setSubcategory(SubcategoryEnum subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public RecipeEntity setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
        return this;
    }

    public int getPortions() {
        return portions;
    }

    public RecipeEntity setPortions(int portions) {
        this.portions = portions;
        return this;
    }

    public String getSteps() {
        return steps;
    }

    public RecipeEntity setSteps(String steps) {
        this.steps = steps;
        return this;
    }

    public int getHours() {
        return hours;
    }

    public RecipeEntity setHours(int hours) {
        this.hours = hours;
        return this;
    }

    public int getMinutes() {
        return minutes;
    }

    public RecipeEntity setMinutes(int minutes) {
        this.minutes = minutes;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public RecipeEntity setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public RecipeEntity setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public RecipeEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public RecipeEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public Set<ImageEntity> getImages() {
        return images;
    }

    public RecipeEntity setImages(Set<ImageEntity> images) {
        this.images = images;
        return this;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public RecipeEntity setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeEntity setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Set<UserEntity> getLikes() {
        return likes;
    }

    public RecipeEntity setLikes(Set<UserEntity> likes) {
        this.likes = likes;
        return this;
    }
}
