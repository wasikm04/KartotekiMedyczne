package pl.medical.service.files.api;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PrescriptionDto {
    private String _id;
    private long prescriptionId;
    private String userMail;
    private String dateTo;
    private String doctorMail;
    private long numberPWZ;
    private String departmentNFZ;
    private String permissions;
    private List<String> medicines;
}
