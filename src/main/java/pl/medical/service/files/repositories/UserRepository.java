package pl.medical.service.files.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.medical.service.files.models.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    User findUserByEmail(String email);
    User findBy_id(ObjectId id);
    User insert(User user);
    User save(User user);
    boolean deleteUserBy_id(ObjectId id);
    boolean existsUserByEmail(String email);
}
