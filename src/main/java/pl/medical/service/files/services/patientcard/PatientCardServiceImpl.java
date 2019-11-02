package pl.medical.service.files.services.patientcard;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.models.User;
import pl.medical.service.files.repositories.patientcard.PatientCardRepository;
import pl.medical.service.files.repositories.user.UserRepository;

@Service
public class PatientCardServiceImpl implements PatientCardService {

    private PatientCardRepository repository;
    private UserRepository userRepository;

    public PatientCardServiceImpl(PatientCardRepository repository,
                                  UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean updateCardInformation(PatientCard card) {
        User user = userRepository.findUserByEmail(card.getUserMail());
        if (user == null || card.getUserId().toString().equals(user.get_id().toString())) {
            if (user != null && !card.getUserMail().equals(user.getEmail())) {
                userRepository.updateMail(card.getUserId(), card.getUserMail());
            }
            return repository.updateCardWithoutArrays(card);
        } else {
            throw new ResourceNotFoundException("Użytkownik o podanym adresie email już istnieje");
        }
    }

    @Override
    public PatientCard getPatientCard(ObjectId id) {
        return repository.getBy_id(id);
    }

    @Override
    public ObjectId addPatientCard(PatientCard card) {
        return repository.savePatientCard(card);
    }

    @Override
    public PatientCard getPatientCardByMail(String mail) {
        return repository.findByUserMail(mail);
    }

    @Override
    public void deleteCard(ObjectId cardId) {
        repository.deleteById(cardId);
    }
}
