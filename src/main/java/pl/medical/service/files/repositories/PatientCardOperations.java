package pl.medical.service.files.repositories;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.PatientCard;

import java.util.List;

public interface PatientCardOperations {
    List<PatientCard> findPatientCardsByKey(String t);
    //PatientCard findPatientCardByUsername(String username);
}
