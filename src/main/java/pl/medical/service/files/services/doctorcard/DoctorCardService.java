package pl.medical.service.files.services.doctorcard;

import pl.medical.service.files.models.DoctorCard;

public interface DoctorCardService {
    boolean addOrUpdateDoctorCard(DoctorCard doctorCard);

    DoctorCard getCardByMail(String userMail);
}
