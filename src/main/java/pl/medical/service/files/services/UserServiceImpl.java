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
    public boolean createUserAccount(User userDto){
        boolean creation = false;
        User addedUser;
        if(userRepository.existsUserByEmail(userDto.getEmail())) {
            User user = new User(passwordEncoder.encode(userDto.getPassword()), userDto.getEmail(), Arrays.asList("ROLE_USER"));
            addedUser = userRepository.insert(user);
            if(addedUser != null && addedUser.getEmail().equals(userDto.getEmail())) {
                creation =  true;
                //insert to patient card z defaultowymi wartościami - stworzenie karty
            }
        }
        return creation;
    }

    @Override
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
