package miu.edu.demo.services;
import com.querydsl.core.types.dsl.BooleanExpression;
import miu.edu.demo.entities.Comment;
import miu.edu.demo.entities.Post;
import miu.edu.demo.entities.User;
import miu.edu.demo.repositories.PostRepository;
import miu.edu.demo.utils.ParamsConverter;
import miu.edu.demo.utils.PredicatesBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public void add(Post post) {
        postRepository.save(post);
    }

    public List<Post> get(Map<String, String> allRequestParams) {
        String searchField = ParamsConverter.convertParamsToString(allRequestParams);
        PredicatesBuilder builder = new PredicatesBuilder(Post.class, "post");


        BooleanExpression exp = builder.build(searchField);
        return (List<Post>) postRepository.findAll(exp);
    }
    public Post get(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public void update(Long id, Post post) {
        post.setId(id);
        postRepository.save(post);
    }

    @Transactional
    public void addComment(Long id, Comment comment) {
        Optional<Post> post = postRepository.findById(id);
        post.ifPresent(p -> {p.getComments().add(comment);});
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

}
