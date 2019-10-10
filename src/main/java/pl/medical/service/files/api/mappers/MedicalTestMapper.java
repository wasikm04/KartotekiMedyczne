package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.MedicalTestDto;
import pl.medical.service.files.models.MedicalTest;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface MedicalTestMapper {

    @Mapping(target = "testDate", source = "testDate", dateFormat = "yyyy-MM-dd")
    MedicalTestDto mapToMedicalTestDto(MedicalTest medicalTest);

    @Mapping(target = "testDate", source = "testDate", dateFormat = "yyyy-MM-dd")
    MedicalTest mapToMedicalTest(MedicalTestDto medicalTestDto);
}
