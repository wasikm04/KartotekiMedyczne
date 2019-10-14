package pl.medical.service.files.services.medicaltest;

import org.springframework.stereotype.Service;
import pl.medical.service.files.models.MedicalTest;
import pl.medical.service.files.repositories.medicaltest.MedicalTestRepository;

@Service
public class MedicalTestServiceImpl implements MedicalTestService {

    private MedicalTestRepository medicalTestRepository;

    public MedicalTestServiceImpl(MedicalTestRepository medicalTestRepository) {
        this.medicalTestRepository = medicalTestRepository;
    }

    @Override
    public boolean addMedicalTestToPatientCard(String userMail, MedicalTest medicalTest) {
        return medicalTestRepository.saveMedicalTestForUser(userMail, medicalTest);
    }

    @Override
    public boolean updateMedicalTestToPatientCard(String userMail, MedicalTest medicalTest) {
        return medicalTestRepository.updateMedicalTest(userMail, medicalTest);
    }
}
