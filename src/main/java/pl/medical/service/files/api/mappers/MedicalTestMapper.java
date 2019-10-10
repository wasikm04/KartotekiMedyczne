package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.MedicalTestDto;
import pl.medical.service.files.models.MedicalTest;

@Mapper(componentModel = "spring")
public interface MedicalTestMapper {

    @Mapping(target = "testDate", source = "testDate", dateFormat = "dd-MM-yyyy")
    MedicalTestDto mapToMedicalTestDto(MedicalTest medicalTest);

    @Mapping(target = "testDate", source = "testDate", dateFormat = "dd-MM-yyyy")
    @Mapping(ignore = true, target = "userMail")
    MedicalTest mapToMedicalTest(MedicalTestDto medicalTestDto);
}
