package pl.medical.service.files.api;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Model recepty")
public class PrescriptionDto {
    private String _id;
    private long prescriptionId;
    @NotNull
    private String userMail;
    private String dateTo;
    private String doctorMail;
    @NotNull
    private long numberPWZ;
    @NotNull
    private String departmentNFZ;
    private String permissions;
    @NotNull
    private List<String> medicines;
}
