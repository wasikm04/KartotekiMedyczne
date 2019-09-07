package pl.medical.service.files.services.patientcard;

import org.springframework.stereotype.Service;
import pl.medical.service.files.repositories.patientcard.PatientCardRepository;

@Service
public class PatientCardServiceImpl implements PatientCardService{

    private PatientCardRepository repository;

    public PatientCardServiceImpl(PatientCardRepository repository){
        this.repository = repository;
    }


}
