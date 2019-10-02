package pl.medical.service.files.repositories.user;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.User;

public interface UserOperations {
    User register(User model);

    void updateMail(ObjectId userid, String mail);
}
