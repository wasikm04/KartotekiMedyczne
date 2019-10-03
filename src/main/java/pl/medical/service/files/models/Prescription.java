package pl.medical.service.files.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class Prescription {
    @Id
    private ObjectId _id;
    private long prescriptionId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateFrom;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTo;
    private String doctorFullName;
    @NotNull
    private long numberPWZ; //in case of searching doctor
    private String departmentNFZ;
    private String permissions;
    @NotNull
    private List<String> medicines;
}
