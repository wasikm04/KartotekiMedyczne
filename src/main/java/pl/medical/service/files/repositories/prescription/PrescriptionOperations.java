package pl.medical.service.files.repositories.prescription;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.Prescription;

import java.util.List;

public interface PrescriptionOperations {
    List<Prescription> getPrescriptionsByUserId(ObjectId userId);

    Prescription findPrescriptionByUserMailAndId(String mail, ObjectId id);

    boolean savePrescription(String mail, Prescription prescription);

    void deletePrescription(String mail, ObjectId id);
}
