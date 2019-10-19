package pl.medical.service.files.services.user;

import org.springframework.stereotype.Service;
import pl.medical.service.files.models.DoctorCard;
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;
import pl.medical.service.files.models.User;
import pl.medical.service.files.repositories.doctorcard.DoctorCardRepository;
import pl.medical.service.files.repositories.patientcard.PatientCardRepository;
import pl.medical.service.files.repositories.user.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PatientCardRepository patientCardRepository;
    private DoctorCardRepository doctorCardRepository;

    public UserServiceImpl(UserRepository userRepository,
                           PatientCardRepository patientCardRepository, DoctorCardRepository doctorCardRepository) {
        this.userRepository = userRepository;
        this.patientCardRepository = patientCardRepository;
        this.doctorCardRepository = doctorCardRepository;
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

    @Override
    public boolean makeUserAsDoctor(String userMail) {
        User user = findUserByEmail(userMail);
        user.getRoles().add("ROLE_DOCTOR");
        User userUp = userRepository.save(user);
        if (userUp != null)
            return Optional.ofNullable(doctorCardRepository.save(DoctorCard.builder().userMail(userMail).build())).isPresent();
        else {
            throw new ResourceNotFoundException("Nieudana operacja dodania roli do użytkownika");
        }
    }
}
