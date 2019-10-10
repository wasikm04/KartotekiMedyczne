package pl.medical.service.files.api;

import lombok.*;

import java.util.List;


@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MedicalTestDto {
    private String _id;
    private String userMail; //wyciągany w kontrolerze do szukania karty
    private String testDate;
    private String testName;
    private String authorMail;
    private List<String> parametersWithReference;
    private String fileId;
}
