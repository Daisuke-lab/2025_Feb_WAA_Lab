package miu.edu.demo.services.specifications;

import jakarta.persistence.criteria.*;
import miu.edu.demo.entities.Post;
import miu.edu.demo.entities.User;


interface Specification<T> {
    Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                          CriteriaBuilder builder);
}


public class UserSpecs {
    public static org.springframework.data.jpa.domain.Specification<User> isPostedCustomer(Integer count) {
        return (root, query, builder) -> {
            Path<Post> posts = root.join("posts", JoinType.LEFT);
            query.groupBy(root.get("id"));
            query.having(builder.greaterThanOrEqualTo(builder.count(posts), (long) count));
            return query.getRestriction();
        };
    }
}
