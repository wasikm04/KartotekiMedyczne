package pl.medical.service.files.api;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class DoctorCardDto {
    private String _id;
    private String userMail;
    private String firstName;
    private String lastName;
    private long numberPWZ;
    private String title;
    private List<String> specializations;
    private long phoneNumber;
    private String prescriptionRefundNumber;
}
