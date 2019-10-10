package pl.medical.service.files.api;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AppointmentDto {
    private String _id;
    private String patientMail;
    private String doctorMail;
    private String doctorFullName;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;
    private String comment;
}
