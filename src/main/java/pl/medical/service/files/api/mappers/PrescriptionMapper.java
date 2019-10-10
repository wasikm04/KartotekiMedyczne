package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.PrescriptionDto;
import pl.medical.service.files.models.Prescription;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface PrescriptionMapper {

    @Mapping(target = "dateTo", source = "dateTo", dateFormat = "yyyy-MM-dd")
    PrescriptionDto mapToPrescriptionDto(Prescription prescription);

    @Mapping(target = "dateTo", source = "dateTo", dateFormat = "yyyy-MM-dd")
    Prescription mapToPrescription(PrescriptionDto prescriptionDto);
}
