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

    public CommentEntity setText(String text) {
        this.text = text;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public CommentEntity setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public CommentEntity setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public UserEntity getOwnerId() {
        return ownerId;
    }

    public CommentEntity setOwnerId(UserEntity ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public RecipeEntity getRecipeId() {
        return recipeId;
    }

    public CommentEntity setRecipeId(RecipeEntity recipeId) {
        this.recipeId = recipeId;
        return this;
    }
}
