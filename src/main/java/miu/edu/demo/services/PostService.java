package miu.edu.demo.services;
import miu.edu.demo.entities.Post;
import miu.edu.demo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public void add(Post post) {
        postRepository.save(post);
    }

    public List<Post> get() {
        return postRepository.findAll();
    }
    public Post get(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public void update(Long id, Post post) {
        post.setId(id);
        postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

}
