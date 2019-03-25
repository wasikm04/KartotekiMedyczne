package pl.medical.service.files.repositories;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.PatientCard;

import java.util.List;

@Repository
public interface PatientCardRepository extends  MongoRepository<PatientCard, ObjectId>, PatientCardOperations{
    PatientCard findBy_id(ObjectId _id);
    List<PatientCard> findAllBy_userid(ObjectId user_id);
}
