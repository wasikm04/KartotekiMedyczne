package pl.medical.service.files.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.medical.service.files.models.User;
import pl.medical.service.files.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUserAccount(User userDto){
        User user = new User(passwordEncoder.encode(userDto.getPassword()),userDto.getEmail(), Arrays.asList("ROLE_USER"));
        return userRepository.insert(user);
    }

    @Override
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
