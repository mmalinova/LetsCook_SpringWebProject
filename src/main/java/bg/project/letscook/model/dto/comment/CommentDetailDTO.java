package bg.project.letscook.model.dto.comment;

public class CommentDetailDTO {

    private Long id;
    private String authorName;
    private String message;

    public CommentDetailDTO() {
    }

    public CommentDetailDTO(Long id, String authorName, String message) {
        this.id = id;
        this.authorName = authorName;
        this.message = message;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
