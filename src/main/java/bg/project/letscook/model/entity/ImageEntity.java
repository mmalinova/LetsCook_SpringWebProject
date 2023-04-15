package bg.project.letscook.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class ImageEntity extends BaseEntity {
    @Column(name = "image_url", nullable = false)
    private String imageURL;
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private RecipeEntity recipeId;

    public String getImageURL() {
        return imageURL;
    }

    public ImageEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public RecipeEntity getRecipeId() {
        return recipeId;
    }

    public ImageEntity setRecipeId(RecipeEntity recipeId) {
        this.recipeId = recipeId;
        return this;
    }
}
