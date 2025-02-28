package miu.edu.demo.services;
import miu.edu.demo.entities.Comment;
import miu.edu.demo.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public void add(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> get() {
        return commentRepository.findAll();
    }
    public Comment get(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public void update(Long id, Comment comment) {
        comment.setId(id);
        commentRepository.save(comment);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

}
