package miu.edu.demo.services;


import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.persistence.criteria.*;
import miu.edu.demo.entities.Post;
import miu.edu.demo.entities.User;
import miu.edu.demo.repositories.UserRepository;
import miu.edu.demo.services.specifications.UserSpecs;
import miu.edu.demo.utils.ParamsConverter;

import miu.edu.demo.utils.PredicatesBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void add(User user) {
        userRepository.save(user);
    }

    public List<User> get() {
        return userRepository.findAll();
    }

    public List<User> get(Map<String, String> allRequestParams) {
        if (allRequestParams.containsKey("postCount")) {
            Integer postCount = Integer.parseInt(allRequestParams.getOrDefault("postCount", "0"));
            //return getUsersWithPosts(postCount);
            return userRepository.findUsersWithPost(postCount);
        }
        String searchField = ParamsConverter.convertParamsToString(allRequestParams);
        PredicatesBuilder builder = new PredicatesBuilder(User.class, "user");


        BooleanExpression exp = builder.build(searchField);
        return (List<User>) userRepository.findAll(exp);
    }

    public List<User> getUsersWithPosts(Integer count) {
        return userRepository.findAll(UserSpecs.isPostedCustomer(count));
    }


    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Post> getPosts(Long id) {
        Optional<User> user = userRepository.findById(id);
        List<Post> posts = user.map(User::getPosts).orElseGet(ArrayList::new);
        posts.size();
        return posts;
    }

    public void update(int id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
