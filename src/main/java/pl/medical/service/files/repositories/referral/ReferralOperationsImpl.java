package pl.medical.service.files.repositories.referral;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.models.Referral;
import pl.medical.service.files.repositories.RepositoryUtils;
import pl.medical.service.files.repositories.patientcard.PatientCardRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ReferralOperationsImpl implements ReferralOperations {

    private MongoOperations mongo;
    private RepositoryUtils repositoryUtils;
    private PatientCardRepository repository;

    public ReferralOperationsImpl(MongoOperations mongo,
                                  RepositoryUtils repositoryUtils,
                                  PatientCardRepository repository) {
        this.mongo = mongo;
        this.repositoryUtils = repositoryUtils;
        this.repository = repository;
    }

    @Override
    public List<Referral> getReferralsByUserId(ObjectId userId) {
        PatientCard card = repository.getBy_id(userId);
        return card.getReferrals();
    }

//    @Override
//    public Referral findReferralByUserMailAndId(String mail, ObjectId id) {
//        PatientCard card = repository.findBy_user_mail(mail);
//        if (card != null) {
//            List<Referral> refs = card.getReferrals();
//            Optional<Referral> test = refs.stream().filter(t -> t.get_id().equals(id)).findFirst();
//            return test.orElse(null);
//        } else {
//            return null;
//        }
//    }

    @Override
    public boolean saveReferral(String mail, Referral refferal) {
        return repositoryUtils.saveObject("referrals", mail, refferal);
    }

    @Override
    public void deleteReferral(String mail, ObjectId id) {
        repositoryUtils.deleteObject("referrals", mail, id);
    }

    @Override
    public boolean updateReferral(String mail, Referral referral) {
        return repositoryUtils.updateObject("referrals", referral.get_id(), referral);
    }
}
