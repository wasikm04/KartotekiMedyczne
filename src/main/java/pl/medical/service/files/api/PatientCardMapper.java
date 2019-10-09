package pl.medical.service.files.api;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PatientCardMapper {

    PatientCardMapper INSTANCE = Mappers.getMapper(PatientCardMapper.class);


}
