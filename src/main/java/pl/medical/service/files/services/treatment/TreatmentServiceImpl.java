package pl.medical.service.files.services.treatment;

import org.springframework.stereotype.Service;
import pl.medical.service.files.models.Treatment;
import pl.medical.service.files.repositories.treatment.TreatmentRepository;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private TreatmentRepository treatmentRepository;

    public TreatmentServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public boolean addTreatmentToPatientCard(String userMail, Treatment treatment) {
        return treatmentRepository.saveTreatment(userMail, treatment);
    }

    @Override
    public boolean updateTreatmentToPatientCard(String userMail, Treatment treatment) {
        return treatmentRepository.updateTreatment(userMail, treatment);
    }
}
