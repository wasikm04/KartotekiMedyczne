package pl.medical.service.files.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ReferralDto {
    private String _id;
    private String date;
    private String userMail;
    private String purpose;
    private String recognition;
    private String doctorMail;
    private long numberPWZ;
}
