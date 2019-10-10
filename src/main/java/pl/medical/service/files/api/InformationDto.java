package pl.medical.service.files.api;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InformationDto {
    private String _id;
    private LocalDate date;
    private String information;
    private String doctorMail;
}
