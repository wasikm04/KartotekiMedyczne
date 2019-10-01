package pl.medical.service.files.repositories.medicaltest;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.MedicalTest;

import java.util.List;

@Repository
public interface MedicalTestRepository extends MongoRepository<MedicalTest, ObjectId>, MedicalTestOperations {

}
