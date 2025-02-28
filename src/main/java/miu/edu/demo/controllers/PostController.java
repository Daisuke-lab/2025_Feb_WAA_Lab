package miu.edu.demo.controllers;

import miu.edu.demo.entities.Post;
import miu.edu.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public List<Post> get(@RequestParam(required = false) Map<String,String> allRequestParams) {
        return postService.get(allRequestParams);
    }

    @GetMapping(path="{id}")
    public Post get(@PathVariable Long id) {
        return postService.get(id);
    }

    @PostMapping(path="{id}")
    public void post(@RequestBody Post post) {
        postService.add(post);
    }
    

    @PutMapping(path="{id}")
    public void put(@PathVariable Long id, @RequestBody Post post) {
        postService.update(id, post);
    }

    @DeleteMapping(path="{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }






}
