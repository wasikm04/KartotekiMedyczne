package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = InformationMapper.class)
public interface TreatmentMapper {
}
