package pl.medical.service.files.api;

import com.mongodb.lang.Nullable;
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
    @Nullable
    private String fileId;
}
