package miu.edu.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import miu.edu.demo.entities.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
}
