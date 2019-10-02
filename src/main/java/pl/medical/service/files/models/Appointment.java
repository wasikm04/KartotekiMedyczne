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
@Document(collection="Appointments")
public class Appointment {

    @Id
    private ObjectId _id;
    private String patient_mail;
    @NotNull
    private String doctor_mail;
    @NotNull
    private String doctorFullName;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy  HH:mm")
    private LocalDateTime date;
    private String comment;
    private boolean available;
}
