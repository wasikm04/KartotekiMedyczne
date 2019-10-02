package pl.medical.service.files.repositories.patientcard;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.*;

public interface PatientCardOperations {

    void updateCardWithoutArrays(PatientCard card);

    void createPatientCard(String mail);
}
