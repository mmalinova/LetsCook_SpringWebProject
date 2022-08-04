package bg.project.letscook.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;
    @Column(name = "created_on", nullable = false)
    private Date createdOn;
    @Column(nullable = false)
    private boolean approved;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity ownerId;
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private RecipeEntity recipeId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public RecipeEntity getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(RecipeEntity recipeId) {
        this.recipeId = recipeId;
    }
}
