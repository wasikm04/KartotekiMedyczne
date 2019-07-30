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
    @Query(value="{ '_user_mail' : ?0}") //fields = "{'_id' : 0}"
    PatientCard findBy_user_mail(String _user_mail);
    //zwrot wewnętrznych obiektów przez wybór pola w oddzielnym repozytorium
   // List<PatientCard> findAllBy_userid(ObjectId user_id);
}
