package pl.medical.service.files.repositories.referral;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.Referral;

public interface ReferralOperations {
    Referral findReferralByUserMailAndId(String mail, ObjectId id);

    boolean saveReferral(String mail, Referral refferal);

    void deleteReferral(String mail, ObjectId id);
}
