package pl.medical.service.files.services.medicaltest;

import pl.medical.service.files.models.MedicalTest;

public interface MedicalTestService {
    String addMedicalTestToPatientCard(String userMail, MedicalTest medicalTest);

    boolean updateMedicalTestToPatientCard(String userMail, MedicalTest medicalTest);
}
