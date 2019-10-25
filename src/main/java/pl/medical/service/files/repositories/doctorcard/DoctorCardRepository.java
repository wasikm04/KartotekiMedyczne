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

    @Query(fields = "{'userMail' : 1, 'firstName' : 1, 'lastName' : 1, 'specializations' : 1, '_id' : 0}")
    Page<DoctorCard> findByFirstNameLikeOrLastNameLikeOrSpecializationsIsLike(String first, String last, String spec, Pageable pageable);

    @Query(fields = "{'userMail' : 1, 'firstName' : 1, 'lastName' : 1, '_id' : 0}")
    List<DoctorCard> findByFirstNameLikeOrLastNameLikeOrSpecializationsIsLike(String firstName, String lastName, String spec);


    @Query(fields = "{'userMail' : 1, 'firstName' : 1, 'lastName' : 1, '_id' : 0}")
    Page<DoctorCard> findBy(TextCriteria criteria, Pageable pageable);


//    TextCriteria criteria1 = TextCriteria.forDefaultLanguage().caseSensitive(false).matching(text);
//    TextCriteria criteria2 = TextCriteria.forDefaultLanguage().caseSensitive(false).matchingPhrase(text);
//    TextCriteria criteria3 = TextCriteria.forDefaultLanguage().caseSensitive(false).matchingAny(text);
    //List<DoctorCard> findByFirstName(TextCriteria criteria, String firstName);
    //List<DoctorCard> l24 = repository.findBy(criteria1, PageRequest.of(0, 5)).getContent();
    //List<DoctorCard> l222 = repository.findBy(criteria2, PageRequest.of(0, 5)).getContent();
    //List<DoctorCard> l211 = repository.findBy(criteria2, PageRequest.of(0, 5)).getContent();


    @Override
    Page<DoctorCard> findAll(Pageable pageable);

    List<DoctorCard> getByFirstNameOrLastNameOrSpecializations(String firstName, String lastName, List<String> specs);

    List<DoctorCard> getBySpecializations(String specialization);

    void deleteBy_id(ObjectId id);

}
