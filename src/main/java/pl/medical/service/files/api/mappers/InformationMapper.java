package pl.medical.service.files.api.mappers;

import org.bson.types.ObjectId;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.medical.service.files.api.InformationDto;
import pl.medical.service.files.models.Information;

import java.util.List;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
abstract class InformationMapper {

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    @Named("mapToInformationDto")
    abstract InformationDto mapToInformationDto(Information information);

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "_id", expression = "java(checkInformationId(informationDto))")
    @Named("mapToInformation")
    abstract Information mapToInformation(InformationDto informationDto);

    ObjectId checkInformationId(InformationDto informationDto) {
        return informationDto.get_id() != null ? new ObjectId(informationDto.get_id()) : ObjectId.get();
    }

    @IterableMapping(qualifiedByName = "mapToInformation")
    abstract List<Information> mapToInformationList(List<InformationDto> informationDTO);

    @IterableMapping(qualifiedByName = "mapToInformationDto")
    abstract List<InformationDto> mapToInformationDtoList(List<Information> information);

}
