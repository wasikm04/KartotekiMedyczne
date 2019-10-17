package pl.medical.service.files.api;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InformationDto {
    private String _id;
    @NotNull
    private LocalDate date;
    @NotNull
    private String information;
    private String doctorMail;
}
