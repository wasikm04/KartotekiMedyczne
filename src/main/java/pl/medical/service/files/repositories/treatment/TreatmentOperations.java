package pl.medical.service.files.repositories.treatment;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.Treatment;

public interface TreatmentOperations {
    Treatment findTreatmentByUserMailAndId(String mail, ObjectId id);

    boolean saveTreatment(String mail, Treatment treatment);

    void deleteTreatment(String mail, ObjectId id);

}
