package pl.medical.service.files.services.doctorcard;

import pl.medical.service.files.models.DoctorCard;

import java.util.List;

public interface DoctorCardService {
    boolean addOrUpdateDoctorCard(DoctorCard doctorCard);

    DoctorCard getCardByMail(String userMail);

    List<DoctorCard> getByLastName(String name);
}
