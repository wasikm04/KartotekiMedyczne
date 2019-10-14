package pl.medical.service.files.services.referral;

import pl.medical.service.files.models.Referral;

public interface ReferralService {
    boolean addReferralToPatientCard(String userMail, Referral referral);

    boolean updateReferralToPatientCard(String userMail, Referral referral);
}
