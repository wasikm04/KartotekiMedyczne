package pl.medical.service.files.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.models.User;

import java.util.List;

@Component
public class PatientCardOperationsImpl implements PatientCardOperations {

    @Autowired
    private MongoOperations mongo;

    //Przykład implementacji, metoda do zmiany
    @Override
    public List<PatientCard> findPatientCardsByKey(String t) { //możliwość porównywania kart po danym polu
            String type = t;
            Criteria where = Criteria.where("key").is(t);
            Query query = Query.query(where);
            return mongo.find(query, PatientCard.class);
        }
/*
    @Override
    public PatientCard findPatientCardByUsername(String username) {
        Criteria usercrit = Criteria.where("username").is(username);
        Query query = Query.query(usercrit);
        User user = mongo.findOne(query, User.class);
        Criteria card = Criteria.where("_userid").is(user.get_id());
        query = Query.query(card);
        return mongo.findOne(query, PatientCard.class);
    }
*/
}

