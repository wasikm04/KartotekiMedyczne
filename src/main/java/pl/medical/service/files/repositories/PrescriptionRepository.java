package pl.medical.service.files.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.Prescription;

import java.util.List;

@Repository
public interface PrescriptionRepository  extends MongoRepository<Prescription, ObjectId>{

    @Query(value="{ '_user_mail' : ?0}",fields = "{'prescriptions' : 1}")
    List<Prescription> findPrescriptionsBy_user_mail(String _user_mail);
    //w razie błędów przenieść metode do PrescriptionsOperationsImpl i ręcznie wyciągać dane

}