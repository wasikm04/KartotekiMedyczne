package pl.medical.service.files.api.mappers;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import pl.medical.service.files.api.DoctorCardDto;
import pl.medical.service.files.models.DoctorCard;

import java.util.List;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface DoctorCardMapper {

    DoctorCardDto mapToDoctorCardDto(DoctorCard doctorCard);

    DoctorCard mapToDoctorCard(DoctorCardDto doctorCardDto);

    @IterableMapping(qualifiedByName = "mapToDoctorCard")
    List<DoctorCardDto> mapToDoctorCardDtoList(List<DoctorCard> cardList);
}
