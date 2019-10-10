package pl.medical.service.files.repositories.treatment;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.MedicalTest;
import pl.medical.service.files.models.Treatment;

import java.util.List;

public interface TreatmentOperations {
    List<Treatment> getTreatmentsByUserId(ObjectId userId);

    // Treatment findTreatmentByUserMailAndId(String mail, ObjectId id);

    boolean saveTreatment(String mail, Treatment treatment);

    void deleteTreatment(String mail, ObjectId id);

}
