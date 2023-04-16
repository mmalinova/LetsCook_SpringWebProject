package bg.project.letscook.web;

import bg.project.letscook.exception.ObjectNotFoundException;
import bg.project.letscook.model.dto.comment.CommentDetailDTO;
import bg.project.letscook.model.dto.comment.CommentMessageDTO;
import bg.project.letscook.model.dto.comment.CreateCommentDTO;
import bg.project.letscook.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{recipeId}/comments")
    public ResponseEntity<List<CommentDetailDTO>> getComments(@PathVariable("recipeId") Long recipeId) {
        return ResponseEntity.ok(commentService.getAllCommentsForRecipe(recipeId));
    }

    @PostMapping(value = "/{recipeId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentDetailDTO> createComment(@PathVariable("recipeId") Long recipeId,
                                                          @AuthenticationPrincipal UserDetails userDetails,
                                                          @RequestBody CommentMessageDTO commentDto) {
        if (userDetails != null && commentDto.getMessage().length() >= 1) {
            CreateCommentDTO commentCreationDto = new CreateCommentDTO(
                    userDetails.getUsername(),
                    recipeId,
                    commentDto.getMessage()
            );
            CommentDetailDTO comment = commentService.createComment(commentCreationDto);
            return ResponseEntity
                    .created(URI.create(String.format("/api/%d/comments/%d", recipeId, comment.getId())))
                    .body(comment);
        }
        return ResponseEntity.badRequest().body(new CommentDetailDTO());
    }

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<ErrorApiResponse> handleRouteNotFound() {
        return ResponseEntity.status(404).body(new ErrorApiResponse("Such route doesn't exist!", 1004));
    }
}

// GET /api/{recipeId}/comments -> returns list of comments for recipe
// POST /api/{recipeId}/comments -> create comment to the recipe and to return the comment just created
// * GET /api/{recipeId}/comments/{commentId} -> returns specific comment by id

class ErrorApiResponse {
    private String message;
    private Integer errorCode;

    public ErrorApiResponse(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ErrorApiResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
