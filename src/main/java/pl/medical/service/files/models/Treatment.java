package pl.medical.service.files.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

//choroba i zalecenia do niej
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class Treatment {
    @Id
    private ObjectId _id;
    @NotNull
    private String numberICD;
    private List<Information> symptomsAndDiagnosis; //objawy i kolejne diagnozy
    private List<Information> pharmacotherapy; //podane leki i przepisane
    private List<Information> medicalAnalysisAndRecommendations; //epikryza, opis kroków i rozpoznania
}
