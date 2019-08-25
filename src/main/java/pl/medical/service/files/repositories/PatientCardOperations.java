package pl.medical.service.files.repositories;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.*;


public interface PatientCardOperations {

    void updateCardWithoutArrays(PatientCard card);

    MedicalTest findMedicalTestByUserMailAndId(String mail,ObjectId id);
    boolean saveMedicalTest(String mail, MedicalTest medtest);
    void deleteMedicalTest(String mail, ObjectId id);

    Prescription findPrescriptionByUserMailAndId(String mail,ObjectId id);
    boolean savePrescription(String mail, Prescription prescription);
    void deletePrescription(String mail, ObjectId id);

    Referral findReferralByUserMailAndId(String mail,ObjectId id);
    boolean saveReferral(String mail, Referral refferal);
    void deleteReferral(String mail, ObjectId id);

    Treatment findTreatmentByUserMailAndId(String mail,ObjectId id);
    boolean saveTreatment(String mail, Treatment treatment);
    void deleteTreatment(String mail, ObjectId id);

}
