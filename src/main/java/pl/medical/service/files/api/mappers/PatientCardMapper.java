package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.PatientCardDto;
import pl.medical.service.files.models.PatientCard;

@Mapper(componentModel = "spring", uses = {PrescriptionMapper.class, ReferralMapper.class, TreatmentMapper.class, MedicalTestMapper.class, ObjectIdMapper.class})
public interface PatientCardMapper {

    // PatientCardMapper INSTANCE = Mappers.getMapper(PatientCardMapper.class);

    @Mapping(target = "dateBirth", source = "dateBirth", dateFormat = "dd-MM-yyyy")
    PatientCardDto mapToPatientCardDto(PatientCard patientCard);

    @Mapping(target = "dateBirth", source = "dateBirth", dateFormat = "dd-MM-yyyy")
    PatientCard mapToPatientCard(PatientCardDto patientCard);

}
