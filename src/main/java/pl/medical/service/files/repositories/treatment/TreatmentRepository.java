package pl.medical.service.files.repositories.treatment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.Treatment;

@Repository
public interface TreatmentRepository extends MongoRepository<Treatment, String>, TreatmentOperations {
}
