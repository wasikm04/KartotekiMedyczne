package pl.medical.service.files.api.mappers;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.medical.service.files.api.InformationDto;
import pl.medical.service.files.models.Information;

import java.util.List;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
interface InformationMapper {

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    @Named("mapToInformationDto")
    InformationDto mapToInformationDto(Information information);

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    @Named("mapToInformation")
    Information mapToInformation(InformationDto informationDto);

    @IterableMapping(qualifiedByName = "mapToInformation")
    List<Information> mapToInformationList(List<InformationDto> informationDTO);

    @IterableMapping(qualifiedByName = "mapToInformationDto")
    List<InformationDto> mapToInformationDtoList(List<Information> information);

}
