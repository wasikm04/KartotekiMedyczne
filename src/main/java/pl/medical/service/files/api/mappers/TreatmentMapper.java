package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.TreatmentDto;
import pl.medical.service.files.models.Treatment;

@Mapper(componentModel = "spring", uses = InformationMapper.class)
public interface TreatmentMapper {

    TreatmentDto mapToTreatmentDto(Treatment treatment);

    @Mapping(ignore = true, target = "userMail")
    Treatment mapToTreatment(TreatmentDto treatmentDto);
}
