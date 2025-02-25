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
        postRepository.add(post);
    }

    public List<Post> get() {
        return postRepository.get();
    }
    public Post get(int id) {
        return postRepository.get(id);
    }

    public void update(int id, Post post) {
        postRepository.update(id, post);
    }

    public void delete(int id) {
        postRepository.delete(id);
    }

}
