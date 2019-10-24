package pl.medical.service.files.repositories.doctorcard;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.DoctorCard;

import java.util.List;

@Repository
public interface DoctorCardRepository extends MongoRepository<DoctorCard,String>, DoctorCardOperations {

    DoctorCard getByUserMail(String userMail);

    @Query(fields = "{'userMail' : 1, 'firstName' : 1, 'lastName' : 1, '_id' : 0}")
    Page<DoctorCard> findByFirstNameLikeOrLastNameLike(String first, String last, Pageable pageable);

    @Query(fields = "{'userMail' : 1, 'firstName' : 1, 'lastName' : 1, '_id' : 0}")
    List<DoctorCard> findByFirstNameOrLastNameOrUserMail(String firstName, String lastName, String mail);

    @Query(fields = "{'userMail' : 1, 'firstName' : 1, 'lastName' : 1, '_id' : 0}")
    List<DoctorCard> findByFirstNameLikeAndLastNameLike(String firstName, String lastName);


    @Query(fields = "{'userMail' : 1, 'firstName' : 1, 'lastName' : 1, '_id' : 0}")
    Page<DoctorCard> findBy(TextCriteria criteria, Pageable pageable);

    //List<DoctorCard> findByFirstName(TextCriteria criteria, String firstName);


    @Override
    Page<DoctorCard> findAll(Pageable pageable);

    List<DoctorCard> getByFirstNameOrLastNameOrSpecializations(String firstName, String lastName, List<String> specs);

    List<DoctorCard> getBySpecializations(String specialization);

    void deleteBy_id(ObjectId id);

}
