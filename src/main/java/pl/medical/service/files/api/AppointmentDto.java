package pl.medical.service.files.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDto {
    private String _id;
    private String patientMail;
    private String doctorMail;
    private String doctorFullName;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;
    private String comment;
}
