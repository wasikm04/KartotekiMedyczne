package pl.medical.service.files.api;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Model wizyty pacjenta u lekarza specjalisty")
public class AppointmentDto {
    private String _id;
    private String patientMail;
    @NotNull
    private String doctorMail;
    @NotNull
    private String doctorFullName;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;
    private String comment;
}
