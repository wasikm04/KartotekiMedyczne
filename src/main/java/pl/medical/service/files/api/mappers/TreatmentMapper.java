package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import pl.medical.service.files.api.TreatmentDto;
import pl.medical.service.files.models.Treatment;

@Mapper(componentModel = "spring", uses = {InformationMapper.class, ObjectIdMapper.class})
public interface TreatmentMapper {

    TreatmentDto mapToTreatmentDto(Treatment treatment);

    Treatment mapToTreatment(TreatmentDto treatmentDto);
}
