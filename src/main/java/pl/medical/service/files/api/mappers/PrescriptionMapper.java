package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.PrescriptionDto;
import pl.medical.service.files.models.Prescription;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {


    PrescriptionDto mapToPrescriptionDto(Prescription prescription);

    @Mapping(ignore = true, target = "userMail")
    Prescription mapToPrescription(PrescriptionDto prescriptionDto);
}
