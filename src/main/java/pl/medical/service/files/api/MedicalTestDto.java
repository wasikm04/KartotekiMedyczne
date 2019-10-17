package pl.medical.service.files.api;

import com.mongodb.lang.Nullable;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MedicalTestDto {
    private String _id;
    @NotNull
    private String userMail; //wyciągany w kontrolerze do szukania karty
    @NotNull
    private String testDate;
    @NotNull
    private String testName;
    @NotNull
    private String authorMail;
    private List<String> parametersWithReference;
    @Nullable
    private String fileId;
}
