package pl.medical.service.files.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.medical.service.files.models.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    User findUserByEmail(String email);
    User insert(User user);
    //delete/update
    boolean existsUserByEmail(String email);
}
