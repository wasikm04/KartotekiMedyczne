package pl.medical.service.files.api;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ReferralDto {
    private String _id;
    private String date;
    private String userMail;
    private String purpose;
    private String recognition;
    private String doctorMail;
    private long numberPWZ;
}
