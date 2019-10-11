package pl.medical.service.files.api;

import lombok.*;
import pl.medical.service.files.models.MedicalTest;
import pl.medical.service.files.models.Prescription;
import pl.medical.service.files.models.Referral;
import pl.medical.service.files.models.Treatment;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
//@Document(collection = "PatientCard")
public class PatientCardDto {
    private String _id;
    private String UserMail;
    private String UserId;
    private String firstName;
    private String lastName;
    private String dateBirth;
    private char sex; //m/w
    private String PESEL;
    private String address;
    private String phoneNumber;
    private String insuranceType;
    private List<Prescription> prescriptions; //recepty
    private List<MedicalTest> medicalTests; //badania
    private List<Referral> referrals; //skierowania
    private List<Treatment> treatments; //choroby i zalecenia
}
