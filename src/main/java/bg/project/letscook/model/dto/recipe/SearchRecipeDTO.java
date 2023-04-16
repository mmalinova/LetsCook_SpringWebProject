package bg.project.letscook.model.dto.recipe;

import bg.project.letscook.model.enums.CategoryEnum;
import bg.project.letscook.model.enums.SubcategoryEnum;

public class SearchRecipeDTO {

    public String name;
    public Integer portions;
    public SubcategoryEnum subcategory;
    public CategoryEnum category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    public SubcategoryEnum getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubcategoryEnum subcategory) {
        this.subcategory = subcategory;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public boolean isEmpty() {
        return (name == null || name.isEmpty()) &&
                portions == null &&
                subcategory == null &&
                category == null;
    }
}

