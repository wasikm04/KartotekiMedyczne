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

@Component
public class RepositoryUtils {

    private MongoOperations mongo;

    public RepositoryUtils(MongoOperations mongo) {
        this.mongo = mongo;
    }

    public void deleteObject(String fieldName, String mail, ObjectId id) {
        Update update = new Update().pull(fieldName,
                new BasicDBObject("_id", id));
        mongo.updateFirst(Query.query(Criteria.where("_user_mail").is(mail)), update, PatientCard.class);
    }

    public boolean saveObject(String fieldName, String mail, Object object) {
        UpdateResult res = mongo.updateFirst(
                Query.query(Criteria.where("_user_mail").is(mail)),
                new Update().push(fieldName, object), PatientCard.class);
        return res.wasAcknowledged();
    }
}
