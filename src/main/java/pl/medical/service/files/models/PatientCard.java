package pl.medical.service.files.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Document(collection="PatientCards")
public class PatientCard {
    @Id
    private ObjectId _id;
    @Email
    @NotNull
    private String _user_mail;
    @NotNull
    private ObjectId _user_id;
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateBirth;
    @NotNull
    private char sex; //m/w
    @PESEL
    @NotNull
    private long PESEL;
    @Nullable
    private String address;
    @Nullable
    private long phoneNumber;
    @NotNull
    private String insuranceType;
    @NotNull
    private List<Prescription> prescriptions; //recepty
    @NotNull
    private List<MedicalTest> medicalTests; //badania
    @NotNull
    private List<Referral> referrals; //skierowania
    @NotNull
    private List<Treatment> treatments; //choroby i zalecenia

}
