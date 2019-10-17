package pl.medical.service.files.api;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ReferralDto {
    private String _id;
    private String date;
    @NotNull
    private String userMail;
    @NotNull
    private String purpose;
    private String recognition;
    @NotNull
    private String doctorMail;
    @NotNull
    private long numberPWZ;
}
