package pl.medical.service.files.repositories.user;

import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.User;

import java.util.Arrays;

@Component
public class UserOperationsImpl implements UserOperations {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserOperationsImpl(UserRepository repository,
                              PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User model) {

        String password = passwordEncoder.encode(model.getPassword());
        User user = User.builder()
                .email(model.getEmail())
                .password(password)
                .roles(Arrays.asList("ROLE_USER"))
                // ._id(ObjectId.get()) //autogenerate?
                .build();

        return repository.insert(user);
    }

    @Override
    public void updateMail(ObjectId userid, String mail) {
        User toUpdate = repository.findBy_id(userid);
        toUpdate.setEmail(mail);
        repository.save(toUpdate);
    }
}
