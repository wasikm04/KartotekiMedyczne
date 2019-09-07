package pl.medical.service.files.services.user;

import pl.medical.service.files.models.User;

public interface UserService {
        boolean createUserAccount(User user);
        User findUserByEmail(String email);
}
