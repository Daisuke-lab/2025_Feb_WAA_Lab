package miu.edu.demo.repositories;

import miu.edu.demo.entities.User;
import miu.edu.demo.entities.UserDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository   extends JpaRepository<UserDetailsModel, Long> {

    UserDetailsModel findByUsername(String username);
}
