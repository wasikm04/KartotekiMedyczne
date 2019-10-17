package pl.medical.service.files.api;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ApiModel(description = "Model karty lekarza")
public class DoctorCardDto {
    private String _id;
    @NotNull
    private String userMail;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private long numberPWZ;
    private String title;
    private List<String> specializations;
    @NotNull
    private long phoneNumber;
    @NotNull
    private String prescriptionRefundNumber;
}
