package pl.medical.service.files.repositories.prescription;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.Prescription;

@Repository
public interface PrescriptionRepository extends MongoRepository<Prescription, ObjectId>, PrescriptionOperations {
}
