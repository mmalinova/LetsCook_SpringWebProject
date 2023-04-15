package bg.project.letscook.model.dto.recipe;

public class SearchRecipeDTO {

    public String productName;
    public String category;
    public Integer portions;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    public boolean isEmpty() {
        return (productName == null || productName.isEmpty()) &&
                (category == null || category.isEmpty()) &&
                portions == null;
    }
}

