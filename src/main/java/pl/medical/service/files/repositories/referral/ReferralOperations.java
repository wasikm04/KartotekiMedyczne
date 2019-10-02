package pl.medical.service.files.repositories.referral;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.Referral;

import java.util.List;

public interface ReferralOperations {
    List<Referral> getReferralsByUserId(ObjectId userId);

    Referral findReferralByUserMailAndId(String mail, ObjectId id);

    boolean saveReferral(String mail, Referral refferal);

    void deleteReferral(String mail, ObjectId id);
}
