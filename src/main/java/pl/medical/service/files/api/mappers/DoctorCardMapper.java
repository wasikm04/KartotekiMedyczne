package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import pl.medical.service.files.api.DoctorCardDto;
import pl.medical.service.files.models.DoctorCard;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface DoctorCardMapper {

    DoctorCardDto mapToDoctorCardDto(DoctorCard doctorCard);

    DoctorCard mapToDoctorCard(DoctorCardDto doctorCardDto);
}
