package pl.medical.service.files.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.medical.service.files.models.MedicalTest;
import pl.medical.service.files.models.Prescription;
import pl.medical.service.files.models.Referral;
import pl.medical.service.files.models.Treatment;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class PatientCardDto {
    private String _id;
    private String _user_mail;
    private String _user_id;
    private String first_name;
    private String last_name;
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
