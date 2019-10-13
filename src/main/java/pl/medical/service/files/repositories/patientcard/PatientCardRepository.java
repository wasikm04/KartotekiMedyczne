package pl.medical.service.files.repositories.patientcard;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.PatientCard;

import java.util.List;

@Repository
public interface PatientCardRepository extends MongoRepository<PatientCard, ObjectId>, PatientCardOperations {

    PatientCard getBy_id(ObjectId id);

    PatientCard findByUserMail(String _user_mail);

    @Query(value = "{ 'userMail' : ?0}", fields = "{'_id' : 1, 'medicalTests' : 1}")
    List<PatientCard> findMedicalTestsBy_user_mail(String _user_mail);
    @Query(value = "{ 'userMail' : ?0, 'medicalTests._id' : ?1}", fields = "{'medicalTests' : 1}")
    PatientCard findMedicalTestBy_user_mailAnd_id(String _user_mail, ObjectId _id);


    @Query(value = "{ 'userMail' : ?0}", fields = "{'_id' : 1, 'referrals' : 1}")
    List<PatientCard> findReferralsBy_user_mail(String _user_mail);
    @Query(value = "{ 'userMail' : ?0, 'referrals._id' : ?1}", fields = "{'referrals' : 1}")
    PatientCard findReferralBy_user_mailAnd_id(String _user_mail, ObjectId id);

    @Query(value = "{ 'userMail' : ?0}", fields = "{'_id' : 1, 'prescriptions' : 1}")
    List<PatientCard> findPrescriptionsBy_user_mail(String _user_mail);
    @Query(value = "{ 'userMail' : ?0, 'prescriptions._id' : ?1}", fields = "{'prescriptions' : 1}")
    PatientCard findPrescriptionBy_user_mailAnd_id(String _user_mail, ObjectId _id);

    @Query(value = "{ 'userMail' : ?0}", fields = "{'_id' : 1, 'treatments' : 1}")
    List<PatientCard> findTreatmentsBy_user_mail(String _user_mail);
    @Query(value = "{ 'userMail' : ?0, 'treatments._id' : ?1}", fields = "{'_id' : 1, 'treatments' : 1}")
    PatientCard findTreatmentBy_user_mailAnd_id(String _user_mail, ObjectId id);

}
