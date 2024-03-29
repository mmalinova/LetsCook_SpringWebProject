package bg.project.letscook.model.dto.recipe;

import java.util.Set;

public class RecipeDetailDTO {

    private Long id;
    private String name;
    private String category;
    private String subcategory;
    private boolean vegetarian;
    private Set<String> images;
    private int portions;
    private String ingredients;
    private String steps;
    private int hours;
    private int minutes;
    private String ownerFirstName;
    private String ownerLastName;
    private String vegetarianString;
    private Set<String> splitSteps;


    public RecipeDetailDTO() {
    }

    public Set<String> getSplitSteps() {
        return splitSteps;
    }

    public void setSplitSteps(Set<String> splitSteps) {
        this.splitSteps = splitSteps;
    }

    public String getVegetarianString() {
        return vegetarianString;
    }

    public void setVegetarianString(String vegetarianString) {
        this.vegetarianString = vegetarianString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
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

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerFullName() {
        return ownerFirstName + " " + ownerLastName;
    }
}
