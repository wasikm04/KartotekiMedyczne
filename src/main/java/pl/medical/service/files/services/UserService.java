package pl.medical.service.files.services;

import pl.medical.service.files.models.User;

public interface UserService {
        User createUserAccount(User user);
        User findUserByEmail(String email);
}
