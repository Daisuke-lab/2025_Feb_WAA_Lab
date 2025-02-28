package miu.edu.demo.services;


import com.querydsl.core.types.dsl.BooleanExpression;
import miu.edu.demo.entities.Post;
import miu.edu.demo.entities.User;
import miu.edu.demo.repositories.UserRepository;
import miu.edu.demo.utils.ParamsConverter;

import miu.edu.demo.utils.PredicatesBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        String searchField = ParamsConverter.convertParamsToString(allRequestParams);
        PredicatesBuilder builder = new PredicatesBuilder(User.class, "user");


        BooleanExpression exp = builder.build(searchField);
        return (List<User>) userRepository.findAll(exp);
    }
    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void update(int id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
