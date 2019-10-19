package pl.medical.service.files.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//wizyta u lekarza | oddzielny kolekcja
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Document(collection = "Appointments")
public class Appointment {
    @Id
    private ObjectId _id;
    private String patientMail;
    @NotNull
    private String doctorMail;
    @NotNull
    private String doctorFullName;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    private String comment;
}
