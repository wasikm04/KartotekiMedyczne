package pl.medical.service.files.repositories.medicaltest;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.MedicalTest;

import java.util.List;

public interface MedicalTestOperations {
    void updateMedicalTestWithFileId(ObjectId testId, ObjectId fileid, String userName);

    List<MedicalTest> getMedicalTestsByUserId(ObjectId userId);

    // MedicalTest findMedicalTestByUserMailAndId(String mail, ObjectId id) throws ResourceNotFoundException;

    String saveMedicalTestForUser(String mail, MedicalTest medtest);

    void deleteMedicalTest(String mail, ObjectId id);

    boolean updateMedicalTest(String mail, MedicalTest medtest);
}