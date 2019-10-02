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
public class MedicalTest {
    @Id
    private ObjectId _id;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate testDate;
    @NotNull
    private String testName;
    @NotNull
    private String authormail;
    private List<String> parametersWithReference;
    private ObjectId fileId;
}
