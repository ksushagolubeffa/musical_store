package services;

import models.AnonymousComment;
import repositories.CommentRepository;

import java.util.List;

public class CommentService {
    private final CommentRepository repository = new CommentRepository();

    public List<AnonymousComment> getAllComments() {
        return repository.findAll();
    }

    public void saveComment(AnonymousComment comment) {
        repository.save(comment);
    }

    public void deleteComment(Long id) {
        repository.delete(id);
    }
}
