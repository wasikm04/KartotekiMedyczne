package pl.medical.service.files.repositories;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.*;

import java.util.List;

@Repository
public interface PatientCardRepository extends  MongoRepository<PatientCard, ObjectId>, PatientCardOperations{

    PatientCard getBy_id(ObjectId id);

    @Query(value="{ '_user_mail' : ?0}")
    PatientCard findBy_user_mail(String _user_mail);


    //przenieść do PatientCardOperations i Impl
    @Query(value="{ '_user_mail' : ?0}",fields = "{'_id' : 1, 'medicalTests' : 1}")
    List<MedicalTest> findMedicalTestsBy_user_mail(String _user_mail);

    @Query(value="{ '_user_mail' : ?0}",fields = "{'_id' : 1, 'referrals' : 1}")
    List<Referral> findReferralsBy_user_mail(String _user_mail);

    @Query(value="{ '_user_mail' : ?0}",fields = "{'_id' : 1, 'prescriptions' : 1}")
    List<Prescription> findPrescriptionsBy_user_mail(String _user_mail);

    @Query(value="{ '_user_mail' : ?0}",fields = "{'_id' : 1, 'treatments' : 1}")
    List<Treatment> findTreatmentsBy_user_mail(String _user_mail);

}
