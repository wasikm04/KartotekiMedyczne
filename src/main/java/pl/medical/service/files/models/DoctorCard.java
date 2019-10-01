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
    private String _user_mail;
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    private long numberPWZ;
    private String title;
    private List<String> specializations;
    @NotNull
    private long phoneNumber;
    @NotNull
    private String prescriptionRefundNumber;
}
