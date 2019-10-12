package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.PatientCardDto;
import pl.medical.service.files.models.PatientCard;

@Mapper(componentModel = "spring", uses = {PrescriptionMapper.class, ObjectIdMapper.class, ReferralMapper.class, TreatmentMapper.class, MedicalTestMapper.class})
public interface PatientCardMapper {


    @Mapping(target = "dateBirth", source = "dateBirth", dateFormat = "yyyy-MM-dd")
    PatientCardDto mapToPatientCardDto(PatientCard patientCard);

    @Mapping(target = "dateBirth", source = "dateBirth", dateFormat = "yyyy-MM-dd")
    PatientCard mapToPatientCard(PatientCardDto patientCard);

}
