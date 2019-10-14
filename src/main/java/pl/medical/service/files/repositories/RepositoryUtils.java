package pl.medical.service.files.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.repositories.patientcard.PatientCardRepository;

import java.util.Optional;

@Component
public class RepositoryUtils {

    private MongoOperations mongo;
    private PatientCardRepository repository;

    public RepositoryUtils(MongoOperations mongo,
                           PatientCardRepository repository) {
        this.mongo = mongo;
        this.repository = repository;
    }

    public void deleteObject(String fieldName, String mail, ObjectId id) {
        Update update = new Update().pull(fieldName,
                new BasicDBObject("_id", id));
        mongo.updateFirst(Query.query(Criteria.where("userMail").is(mail)), update, PatientCard.class);
    }

    public boolean saveObject(String fieldName, String mail, Object object) {
        UpdateResult res = mongo.updateFirst(
                Query.query(Criteria.where("userMail").is(mail)),
                new Update().push(fieldName, object), PatientCard.class);
        return res.wasAcknowledged();
    }

    public boolean updateObject(String fieldName, ObjectId objectId, Object toUpdate) {
        Query query = new Query(Criteria.where(fieldName + "._id").is(objectId));
        Update update = new Update().set(fieldName + ".$", toUpdate);
        PatientCard card = mongo.findAndModify(query, update, PatientCard.class);
        return Optional.ofNullable(card).isPresent();
    }
}
