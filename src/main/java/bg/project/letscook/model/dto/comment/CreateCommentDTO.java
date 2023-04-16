package bg.project.letscook.model.dto.comment;

public class CreateCommentDTO {

    private String username;
    private Long recipeId;
    private String message;

    public CreateCommentDTO(String username, Long recipeId, String message) {
        this.username = username;
        this.recipeId = recipeId;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
