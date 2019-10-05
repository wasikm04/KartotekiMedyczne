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
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;
import pl.medical.service.files.repositories.RepositoryUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class PatientCardOperationsImpl implements PatientCardOperations {

    private MongoOperations mongo;

    public PatientCardOperationsImpl(MongoOperations mongo) {
        this.mongo = mongo;
    }

    @Override
    public void updateCardWithoutArrays(PatientCard updatedCard) throws ResourceNotFoundException {
        Query query = new Query();
        query.addCriteria(Criteria.where("_user_id").is(updatedCard.get_user_id()));
        PatientCard card = mongo.findOne(query, PatientCard.class);
        if (card != null) {
            updatedCard.setMedicalTests(Optional.ofNullable(card.getMedicalTests()).orElseGet(ArrayList::new));
            updatedCard.setPrescriptions(Optional.ofNullable(card.getPrescriptions()).orElseGet(ArrayList::new));
            updatedCard.setReferrals(Optional.ofNullable(card.getReferrals()).orElseGet(ArrayList::new));
            updatedCard.setTreatments(Optional.ofNullable(card.getTreatments()).orElseGet(ArrayList::new));
            mongo.save(updatedCard);
        } else {
            throw new ResourceNotFoundException("Brak karty pacjenta o podanym id " + updatedCard.get_user_id());
        }
    }

    @Override
    public ObjectId savePatientCard(PatientCard card) {
        return mongo.save(card).get_user_id();
    }

    @Override
    public void createPatientCard(User user) {
        mongo.save(PatientCard
                .builder()
                ._user_mail(user.getEmail())
                ._user_id(user.get_id())
                .build()
        );
    }
}

