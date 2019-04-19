package pl.medical.service.files.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.medical.service.files.models.User;

public interface UserRepository extends MongoRepository<User,String> {
    User findUserByEmail(String email);
}
