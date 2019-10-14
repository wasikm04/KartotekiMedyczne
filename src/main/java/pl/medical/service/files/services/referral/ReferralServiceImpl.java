package pl.medical.service.files.services.referral;

import org.springframework.stereotype.Service;
import pl.medical.service.files.models.Referral;
import pl.medical.service.files.repositories.referral.ReferralRepository;

@Service
public class ReferralServiceImpl implements ReferralService {

    private ReferralRepository referralRepository;

    public ReferralServiceImpl(ReferralRepository referralRepository) {
        this.referralRepository = referralRepository;
    }

    @Override
    public boolean addReferralToPatientCard(String userMail, Referral referral) {
        return referralRepository.saveReferral(userMail, referral);
    }

    @Override
    public boolean updateReferralToPatientCard(String userMail, Referral referral) {
        return referralRepository.updateReferral(userMail, referral);
    }
}
