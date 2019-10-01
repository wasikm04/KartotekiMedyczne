package pl.medical.service.files.repositories.medicaltest;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.MedicalTest;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.repositories.RepositoryUtils;
import pl.medical.service.files.repositories.patientcard.PatientCardRepository;

import java.util.List;
import java.util.Optional;

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
    public ObjectId AddMedicalTestForUserWithMail(MedicalTest test, String userMail) {
        return null;
    }

    @Override
    public void UpdateMedicalTestWithFileId(ObjectId testId, ObjectId fileid) {

    }

    @Override
    public List<MedicalTest> getMedicalTestsByUserId(ObjectId userId) {
        return null;
    }

    @Override
    public MedicalTest findMedicalTestByUserMailAndId(String mail, ObjectId id) {
        PatientCard card = repository.findBy_user_mail(mail);
        if (card != null) {
            List<MedicalTest> tests = card.getMedicalTests();
            Optional<MedicalTest> test = tests.stream().filter(t -> t.get_id().equals(id)).findFirst();
            return test.orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public boolean saveMedicalTest(String mail, MedicalTest medtest) {
        return repositoryUtils.saveObject("medicalTests", mail, medtest);
    }

    @Override
    public void deleteMedicalTest(String mail, ObjectId id) {
        repositoryUtils.deleteObject("medicalTests", mail, id);
    }
}
