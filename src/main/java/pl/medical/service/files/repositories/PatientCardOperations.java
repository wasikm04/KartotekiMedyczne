package pl.medical.service.files.repositories;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.*;

import java.util.List;

public interface PatientCardOperations {
    MedicalTest findMedicalTestByUserMailAndId(String mail,ObjectId id);
    //save/update/delete
    Prescription findPrescriptionByUserMailAndId(String mail,ObjectId id);
    //save/update/delete
    Referral findReferralByUserMailAndId(String mail,ObjectId id);
    //save/update/delete
    Treatment findTreatmentByUserMailAndId(String mail,ObjectId id);
    //save/update/delete
}
