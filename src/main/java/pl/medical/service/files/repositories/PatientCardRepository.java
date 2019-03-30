package pl.medical.service.files.repositories;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.PatientCard;

import java.util.List;

@Repository
public interface PatientCardRepository extends  MongoRepository<PatientCard, ObjectId>, PatientCardOperations{
    PatientCard findBy_id(ObjectId _id);
   // @Query("{ 'first_name' : 1, 'last_name' : 1, 'key' : 1 , '_username':1 ,'_id' : 0}")
    PatientCard findBy_username(String _username);
   // List<PatientCard> findAllBy_userid(ObjectId user_id);
}
