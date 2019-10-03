package pl.medical.service.files.services.user;

import org.springframework.stereotype.Service;
import pl.medical.service.files.models.User;
import pl.medical.service.files.repositories.patientcard.PatientCardRepository;
import pl.medical.service.files.repositories.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PatientCardRepository patientCardRepository;

    public UserServiceImpl(UserRepository userRepository,
                           PatientCardRepository patientCardRepository) {
        this.userRepository = userRepository;
        this.patientCardRepository = patientCardRepository;
    }

    @Override
    public boolean createUserAccount(User userDto) {
        boolean creation = false;
        if (!userRepository.existsUserByEmail(userDto.getEmail())) {
            User addedUser = userRepository.register(userDto);
            if (addedUser != null && addedUser.get_id() != null && addedUser.getEmail().equals(userDto.getEmail())) {
                patientCardRepository.createPatientCard(addedUser);
                creation = true;
            }
        }
        return creation;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
