package pl.medical.service.files.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

//oddzielna kolekcja
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Document(collection="DoctorCards")
public class DoctorCard {
    @Id
    private ObjectId _id;
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
