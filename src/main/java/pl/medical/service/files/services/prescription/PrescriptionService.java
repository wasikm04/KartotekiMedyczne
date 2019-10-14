package pl.medical.service.files.services.prescription;

import pl.medical.service.files.models.Prescription;

public interface PrescriptionService {
    boolean addPrescriptionToPatientCard(String userMail, Prescription prescription);

    boolean updatePrescriptionToPatientCard(String userMail, Prescription prescription);
}
