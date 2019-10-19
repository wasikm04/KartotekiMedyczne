package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.medical.service.files.api.MedicalTestDto;
import pl.medical.service.files.models.MedicalTest;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicalTestMapper {

    @Mapping(target = "testDate", source = "testDate", dateFormat = "yyyy-MM-dd")
    MedicalTestDto mapToMedicalTestDto(MedicalTest medicalTest);

    @Mapping(target = "testDate", source = "testDate", dateFormat = "yyyy-MM-dd")
    MedicalTest mapToMedicalTest(MedicalTestDto medicalTestDto);

//    @IterableMapping(qualifiedByName = "mapToMedicalTest")
//    @Named("mapToMedicalTestList")
//    List<MedicalTest> mapToMedicalTestList(List<MedicalTestDto> medicalTestDTO);
//
//    @IterableMapping(qualifiedByName = "mapToMedicalTestDto")
//    @Named("mapToMedicalTestDtoList")
//    List<MedicalTestDto> mapToMedicalTestDtoList(List<MedicalTest> medicalTest);
}
