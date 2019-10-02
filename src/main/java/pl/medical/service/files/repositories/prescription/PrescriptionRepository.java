package pl.medical.service.files.repositories.prescription;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.Prescription;

import java.util.List;

@Repository
public interface PrescriptionRepository extends MongoRepository<Prescription, ObjectId>, PrescriptionOperations {
    Prescription findPrescriptionBy_id(ObjectId id);
}
