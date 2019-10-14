package pl.medical.service.files.services.prescription;

import org.springframework.stereotype.Service;
import pl.medical.service.files.models.Prescription;
import pl.medical.service.files.repositories.prescription.PrescriptionRepository;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private PrescriptionRepository prescriptionRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public boolean addPrescriptionToPatientCard(String userMail, Prescription prescription) {
        return prescriptionRepository.savePrescriptionInPatientCard(userMail, prescription);
    }

    @Override
    public boolean updatePrescriptionToPatientCard(String userMail, Prescription prescription) {
        return prescriptionRepository.updatePrescription(userMail, prescription);
    }
}
