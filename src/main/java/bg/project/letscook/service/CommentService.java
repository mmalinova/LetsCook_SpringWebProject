package bg.project.letscook.service;

import bg.project.letscook.model.dto.comment.CommentDetailDTO;
import bg.project.letscook.model.dto.comment.CreateCommentDTO;
import bg.project.letscook.model.entity.CommentEntity;
import bg.project.letscook.model.entity.RecipeEntity;
import bg.project.letscook.model.entity.UserEntity;
import bg.project.letscook.repository.CommentRepository;
import bg.project.letscook.repository.RecipeRepository;
import bg.project.letscook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private RecipeRepository recipeRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    public CommentService(RecipeRepository recipeRepository, UserRepository userRepository,
                          CommentRepository commentRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public List<CommentDetailDTO> getAllCommentsForRecipe(Long recipeId) {
        RecipeEntity recipe = recipeRepository.findById(recipeId).orElseThrow();

        Set<CommentEntity> comments = commentRepository.findAllByRecipe(recipe);
        return comments.stream().map(comment -> new CommentDetailDTO(
                comment.getId(), comment.getOwner().getFirstName() + " " + comment.getOwner().getLastName(),
                comment.getText())).collect(Collectors.toList());
    }

    public CommentDetailDTO createComment(CreateCommentDTO commentDto) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        UserEntity author = userRepository.findByEmail(commentDto.getUsername()).orElseThrow();

        CommentEntity comment = new CommentEntity();
        comment.setCreatedOn(date);
        comment.setRecipe(recipeRepository.findById(commentDto.getRecipeId()).orElseThrow());
        comment.setOwner(author);
        comment.setApproved(true);
        comment.setText(commentDto.getMessage());

        commentRepository.save(comment);

        return new CommentDetailDTO(comment.getId(), author.getFirstName() + " " + author.getLastName(), comment.getText());
    }
}
