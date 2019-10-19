package pl.medical.service.files.api;

import com.mongodb.lang.Nullable;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Model badania lekarskiego")
public class MedicalTestDto {
    private String _id;
    @NotNull
    private String userMail;
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
