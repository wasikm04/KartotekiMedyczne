package pl.medical.service.files.repositories.medicaltest;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.MedicalTest;

import java.util.List;

public interface MedicalTestOperations {
    ObjectId AddMedicalTestForUserWithMail(MedicalTest test, String userMail);

    void UpdateMedicalTestWithFileId(ObjectId testId, ObjectId fileid);

    List<MedicalTest> getMedicalTestsByUserId(ObjectId userId);

    MedicalTest findMedicalTestByUserMailAndId(String mail, ObjectId id);

    boolean saveMedicalTest(String mail, MedicalTest medtest);

    void deleteMedicalTest(String mail, ObjectId id);
}
