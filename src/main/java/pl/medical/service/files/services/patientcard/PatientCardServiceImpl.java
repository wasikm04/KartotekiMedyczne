package pl.medical.service.files.services.patientcard;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
    public void updateCardInformation(PatientCard card) {
        User user = userRepository.findUserByEmail(card.getUserMail());
        try {
            userRepository.updateMail(card.getUserId(), card.getUserMail());
            repository.updateCardWithoutArrays(card);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
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
