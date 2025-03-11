package miu.edu.demo.repositories;

import miu.edu.demo.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthUser, Long> {

    AuthUser findByUsername(String username);

    boolean existsByUsername(String username);
}
