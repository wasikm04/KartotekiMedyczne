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
        User user = userRepository.findUserByEmail(card.get_user_mail());
        try {
            userRepository.updateMail(card.get_user_id(), card.get_user_mail());
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
        return repository.findBy_user_mail(mail);
    }

    @Override
    public void deleteCard(ObjectId cardId) {
        repository.deleteById(cardId);
    }
}
