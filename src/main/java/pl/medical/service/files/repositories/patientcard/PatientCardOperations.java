package pl.medical.service.files.repositories.patientcard;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.models.User;

public interface PatientCardOperations {

    void updateCardWithoutArrays(PatientCard card) throws ResourceNotFoundException;
    ObjectId savePatientCard(PatientCard card);
    void createPatientCard(User user);
}
