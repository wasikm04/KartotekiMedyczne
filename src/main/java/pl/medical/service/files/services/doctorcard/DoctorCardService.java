package pl.medical.service.files.services.doctorcard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.medical.service.files.models.DoctorCard;

public interface DoctorCardService {
    boolean addOrUpdateDoctorCard(DoctorCard doctorCard);

    DoctorCard getCardByMail(String userMail);

    Page<DoctorCard> getByFirstOrLastName(String name, PageRequest request);

    Page<DoctorCard> getPage(PageRequest request);
}
