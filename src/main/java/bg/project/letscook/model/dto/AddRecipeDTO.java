package bg.project.letscook.model.dto;

import bg.project.letscook.model.enums.CategoryEnum;
import bg.project.letscook.model.enums.SubcategoryEnum;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddRecipeDTO {
    @NotBlank
    private String name;
    @NotNull
    private CategoryEnum category;
    @NotNull
    private SubcategoryEnum subcategory;
    @NotNull
    private boolean vegetarian;
    @NotBlank
    private String imageURL;
    @NotNull
    @Min(1)
    private int portions;
    @NotBlank
    private String productName;
    @NotNull
    @Positive
    private float quantity;
    @NotBlank
    private String measureUnit;
    @NotBlank
    private String steps;
    @NotNull
    @Positive
    private int hours;
    @NotNull
    @Positive
    private int minutes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
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
}
