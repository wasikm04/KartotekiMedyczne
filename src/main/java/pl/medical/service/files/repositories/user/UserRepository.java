package pl.medical.service.files.repositories.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>, UserOperations {
    User findUserByEmail(String email);
    User findBy_id(ObjectId id);
    void deleteUserBy_id(ObjectId id);
    boolean existsUserByEmail(String email);
}
