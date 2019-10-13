package pl.medical.service.files.repositories.treatment;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.MedicalTest;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.models.Treatment;
import pl.medical.service.files.repositories.RepositoryUtils;
import pl.medical.service.files.repositories.patientcard.PatientCardRepository;

import java.util.List;
import java.util.Optional;

@Component
public class TreatmentOperationsImpl implements TreatmentOperations {

    private MongoOperations mongo;
    private PatientCardRepository repository;
    private RepositoryUtils repositoryUtils;

    public TreatmentOperationsImpl(PatientCardRepository repository,
                                   MongoOperations mongo,
                                   RepositoryUtils repositoryUtils) {
        this.repository = repository;
        this.mongo = mongo;
        this.repositoryUtils = repositoryUtils;
    }

    @Override
    public List<Treatment> getTreatmentsByUserId(ObjectId userId) {
        PatientCard card = repository.getBy_id(userId);
        return card.getTreatments();
    }

    //Metody obsługi Information(?) (add/update/delete/get)
//    @Override
//    public Treatment findTreatmentByUserMailAndId(String mail, ObjectId id) {
//        PatientCard card = repository.findBy_user_mail(mail);
//        if (card != null) {
//            List<Treatment> treatments = card.getTreatments();
//            Optional<Treatment> test = treatments.stream().filter(t -> t.get_id().equals(id)).findFirst();
//            return test.orElse(null);
//        } else {
//            return null;
//        }
//    }

    @Override
    public boolean saveTreatment(String mail, Treatment treatment) {
        return repositoryUtils.saveObject("treatments", mail, treatment);
    }

    @Override
    public void deleteTreatment(String mail, ObjectId id) {
        repositoryUtils.deleteObject("treatments", mail, id);
    }

    @Override
    public boolean updateTreatment(String mail, Treatment treatment) {
        return repositoryUtils.updateObject("treatments", treatment.get_id(), treatment);
    }

}
