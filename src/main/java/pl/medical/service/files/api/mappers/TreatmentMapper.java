package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.TreatmentDto;
import pl.medical.service.files.models.Treatment;

@Mapper(componentModel = "spring", uses = {InformationMapper.class, ObjectIdMapper.class})
public interface TreatmentMapper {

    @Mapping(target = "symptomsAndDiagnosis", source = "symptomsAndDiagnosis")
    @Mapping(target = "pharmacotherapy", source = "pharmacotherapy")
    @Mapping(target = "medicalAnalysisAndRecommendations", source = "medicalAnalysisAndRecommendations")
    TreatmentDto mapToTreatmentDto(Treatment treatment);

    @Mapping(target = "symptomsAndDiagnosis", source = "symptomsAndDiagnosis")
    @Mapping(target = "pharmacotherapy", source = "pharmacotherapy")
    @Mapping(target = "medicalAnalysisAndRecommendations", source = "medicalAnalysisAndRecommendations")
    Treatment mapToTreatment(TreatmentDto treatmentDto);
}
