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
@ApiModel(description = "Model kartoteki pacjenta")
public class PatientCardDto {
    private String _id;
    @NotNull
    private String userMail;
    @NotNull
    private String userId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String dateBirth;
    @NotNull
    private String sex; //m/w
    @NotNull
    private String PESEL;
    private String address;
    private String phoneNumber;
    private String insuranceType;
    private List<PrescriptionDto> prescriptions; //recepty
    private List<MedicalTestDto> medicalTests; //badania
    private List<ReferralDto> referrals; //skierowania
    private List<TreatmentDto> treatments; //choroby i zalecenia
}
