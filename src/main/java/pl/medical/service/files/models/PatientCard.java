package pl.medical.service.files.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Document(collection = "PatientCards")
public class PatientCard {
    @Id
    private ObjectId _id;
    @Email
    @NotNull
    private String userMail;
    @NotNull
    private ObjectId userId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateBirth;
    @NotNull
    private String sex;
    @PESEL
    @NotNull
    private String PESEL;
    private String address;
    private String phoneNumber;
    private String insuranceType;
    private List<Prescription> prescriptions; //recepty
    private List<MedicalTest> medicalTests; //badania
    private List<Referral> referrals; //skierowania
    private List<Treatment> treatments; //choroby i zalecenia

}
