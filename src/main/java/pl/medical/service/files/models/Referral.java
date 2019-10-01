package pl.medical.service.files.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

//skierowanie
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class Referral {
    @Id
    private ObjectId _id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @NotNull
    private String purpose; //cel: badania/poradnia specjalistyczna
    private String recognition;
    @NotNull
    private String doctorFullName;
    @NotNull
    private long numberPWZ; //in case of searching doctor
}
