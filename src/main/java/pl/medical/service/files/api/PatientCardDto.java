package pl.medical.service.files.api;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
//@Document(collection = "PatientCard")
public class PatientCardDto {
    private String _id;
    private String userMail;
    private String userId;
    private String firstName;
    private String lastName;
    private String dateBirth;
    private char sex; //m/w
    private String PESEL;
    private String address;
    private String phoneNumber;
    private String insuranceType;
    private List<PrescriptionDto> prescriptions; //recepty
    private List<MedicalTestDto> medicalTests; //badania
    private List<ReferralDto> referrals; //skierowania
    private List<TreatmentDto> treatments; //choroby i zalecenia
}
