package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.InformationDto;
import pl.medical.service.files.models.Information;

@Mapper(componentModel = "spring")
interface InformationMapper {

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy")
    InformationDto mapToInformationDto(Information information);

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy")
    Information mapToInformation(InformationDto informationDto);
}
