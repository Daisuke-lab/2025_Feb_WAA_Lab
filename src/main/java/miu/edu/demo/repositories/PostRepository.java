package miu.edu.demo.repositories;

import miu.edu.demo.entities.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private List<Post> posts;

    public PostRepository() {
        initPosts();
    }
    public void initPosts() {
        posts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Post post = new Post(i, "title" + i, "content" + i, "author" + i);
            posts.add(post);
        }
    }

    public void add(Post post) {
        post.setId(posts.get(posts.size() - 1).getId() + 1);
        posts.add(post);
    }

    public Post get(int id) {
        return posts.stream().filter(post -> post.getId() == id).findAny().orElse(null);
    }

    public List<Post> get() {
        return posts;
    }

    public void update(int id, Post post) {
        int i = posts.indexOf(get(id));
        if (i != -1) {
            post.setId(id);
            posts.set(i, post);
        }
    }

    public void delete(int id) {
        int i = posts.indexOf(get(id));
        if (i != -1) {
            posts.remove(i);
        }
    }


}
