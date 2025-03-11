package miu.edu.demo.controllers;


import miu.edu.demo.aspects.ExecutionTime;
import miu.edu.demo.entities.Post;
import miu.edu.demo.entities.User;
import miu.edu.demo.repositories.UserRepository;
import miu.edu.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> get(@RequestParam(required = false) Map<String,String> allRequestParams) {
        return userService.get(allRequestParams);
    }

    @GetMapping(path ="test")
    public List<Post> test() {
        System.out.println("you are here");
        List<Post> users = userRepository.findUsersWTest();
        System.out.println("USERS:");
        System.out.println(users);
        return users;
    }

    @ExecutionTime
    @GetMapping(path="{id}")
    public User get(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping(path="{id}/posts")
    public List<Post> getPosts(@PathVariable("id") Long id) {
        return userService.getPosts(id);
    }


    @PostMapping
    public void user(@RequestBody User user) {
        userService.add(user);
    }


    @PutMapping(path="{id}")
    public void put(@PathVariable int id, @RequestBody User user) {
        userService.update(id, user);
    }

    @DeleteMapping(path="{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
