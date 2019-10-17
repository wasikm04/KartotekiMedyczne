package pl.medical.service.files.services.doctorcard;

import org.springframework.stereotype.Service;
import pl.medical.service.files.models.DoctorCard;
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;
import pl.medical.service.files.repositories.doctorcard.DoctorCardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorCardServiceImpl implements DoctorCardService {

    private DoctorCardRepository doctorCardRepository;

    public DoctorCardServiceImpl(DoctorCardRepository doctorCardRepository) {
        this.doctorCardRepository = doctorCardRepository;
    }

    @Override
    public boolean addOrUpdateDoctorCard(DoctorCard doctorCard) {
        return Optional.ofNullable(doctorCardRepository.save(doctorCard)).isPresent();
    }

    @Override
    public DoctorCard getCardByMail(String userMail) {
        return Optional.ofNullable(doctorCardRepository.getByUserMail(userMail)).orElseThrow(() -> new ResourceNotFoundException("Brak karty lekarza dla podanego maila"));
    }

    @Override
    public List<DoctorCard> getByLastName(String name) {
        return doctorCardRepository.getAllByLastNameOrderByLastName(name);
    }
}
