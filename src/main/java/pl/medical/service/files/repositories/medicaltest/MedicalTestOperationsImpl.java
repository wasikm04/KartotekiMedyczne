package pl.medical.service.files.repositories.medicaltest;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;
import pl.medical.service.files.models.MedicalTest;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.repositories.RepositoryUtils;
import pl.medical.service.files.repositories.patientcard.PatientCardRepository;

import java.util.List;

@Component
public class MedicalTestOperationsImpl implements MedicalTestOperations {

    private MongoOperations mongo;
    private RepositoryUtils repositoryUtils;
    private PatientCardRepository repository;

    public MedicalTestOperationsImpl(MongoOperations mongo,
                                     RepositoryUtils repositoryUtils,
                                     PatientCardRepository repository) {
        this.mongo = mongo;
        this.repositoryUtils = repositoryUtils;
        this.repository = repository;
    }


    @Override
    public void updateMedicalTestWithFileId(ObjectId testId, ObjectId fileid, String userName) {
        PatientCard card = repository.findMedicalTestBy_user_mailAnd_id(userName, testId);

        if (card == null) {
            throw new ResourceNotFoundException("Brak testu o podanym ID " + fileid);
        } else {
            MedicalTest test = card.getMedicalTests().get(0);
            if (test != null) {
                test.setFileId(fileid);
                repositoryUtils.updateObject("medicalTests", testId, test);
                // Query query = new Query(Criteria.where("medicalTests._id").is(testId));
                // Update update = new Update().set("medicalTests.$", test);
                // this.mongo.findAndModify(query, update, PatientCard.class);
            }
        }
    }

    @Override
    public List<MedicalTest> getMedicalTestsByUserId(ObjectId userId) {
        PatientCard card = repository.getBy_id(userId);
        return card.getMedicalTests();
    }

//    @Override
//    public MedicalTest findMedicalTestByUserMailAndId(String mail, ObjectId id) throws ResourceNotFoundException {
//        PatientCard card = repository.findByUserMail(mail);
//        if (card != null) {
//            List<MedicalTest> tests = card.getMedicalTests();
//            Optional<MedicalTest> test = tests.stream().filter(t -> t.get_id().equals(id)).findFirst();
//            return test.orElseThrow(() -> new ResourceNotFoundException("Brak testu o podanym id " + id));
//        } else {
//            throw new ResourceNotFoundException("Brak karty pacjenta o podanym mailu " + mail);
//        }
//    }

    @Override
    public boolean saveMedicalTestForUser(String mail, MedicalTest medtest) {
        return repositoryUtils.saveObject("medicalTests", mail, medtest);
    }

    @Override
    public void deleteMedicalTest(String mail, ObjectId id) {
        repositoryUtils.deleteObject("medicalTests", mail, id);
    }
}
