package pl.medical.service.files.repositories.patientcard;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.*;
import pl.medical.service.files.repositories.RepositoryUtils;

import java.util.List;
import java.util.Optional;

@Component
public class PatientCardOperationsImpl implements PatientCardOperations {

    private MongoOperations mongo;
    private RepositoryUtils repositoryUtils;
    private PatientCardRepository repository;

    public PatientCardOperationsImpl(MongoOperations mongo,
                                     PatientCardRepository repository) {
        this.repositoryUtils = new RepositoryUtils(mongo);
        this.mongo = mongo;
        this.repository = repository;
    }

    @Override
    public void updateCardWithoutArrays(PatientCard updatedCard) {
        PatientCard card = repository.findBy_user_mail(updatedCard.get_user_mail());
        updatedCard.setMedicalTests(card.getMedicalTests());
        updatedCard.setPrescriptions(card.getPrescriptions());
        updatedCard.setReferrals(card.getReferrals());
        updatedCard.setTreatments(card.getTreatments());
        mongo.save(updatedCard);
    }
}

