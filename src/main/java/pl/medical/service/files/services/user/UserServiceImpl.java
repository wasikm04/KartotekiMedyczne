package pl.medical.service.files.services.user;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.medical.service.files.models.User;
import pl.medical.service.files.repositories.user.UserRepository;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createUserAccount(User userDto) {
        boolean creation = false;
        User addedUser;
        if (userRepository.existsUserByEmail(userDto.getEmail())) {
            String password = passwordEncoder.encode(userDto.getPassword());
            User user = User.builder()
                    .email(userDto.getEmail())
                    .password(password)
                    .roles(Arrays.asList("ROLE_USER"))
                    ._id(ObjectId.get()) //autogenerate?
                    .build();
            //new User(userDto.getEmail(), password, Arrays.asList("ROLE_USER"));
            addedUser = userRepository.insert(user);
            if (addedUser != null && addedUser.getEmail().equals(userDto.getEmail())) {
                creation = true;
                //insert to patient card z defaultowymi wartościami - stworzenie karty
            }
        }
        return creation;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
