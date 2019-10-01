package pl.medical.service.files.repositories.referral;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.Referral;

@Repository
public interface ReferralRepository extends MongoRepository<Referral, ObjectId>, ReferralOperations {
}
