package bg.project.letscook.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "ingredient")
public class IngredientEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(name = "measure_unit", nullable = false)
    private String measureUnit;
    @Column(nullable = false)
    private float quantity;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = true)
    private UserEntity ownerId;
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = true)
    private RecipeEntity recipeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public UserEntity getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UserEntity ownerId) {
        this.ownerId = ownerId;
    }

    public RecipeEntity getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(RecipeEntity recipeId) {
        this.recipeId = recipeId;
    }
}
