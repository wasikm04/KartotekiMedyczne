package pl.medical.service.files.services.treatment;

import pl.medical.service.files.models.Treatment;

public interface TreatmentService {
    boolean addTreatmentToPatientCard(String userMail, Treatment treatment);

    boolean updateTreatmentToPatientCard(String userMail, Treatment treatment);
}
