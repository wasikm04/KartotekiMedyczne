package pl.medical.service.files.repositories.doctorcard;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(fields = "{'userMail' : 1, 'firstName' : 1, 'lastName' : 1, 'specializations' : 1, '_id' : 0}")
    Page<DoctorCard> findByFirstNameLikeOrLastNameLikeOrSpecializations(String first, String last, String spec, Pageable pageable);

    @Query(fields = "{'userMail' : 1, 'firstName' : 1, 'lastName' : 1,'specializations' : 1, '_id' : 0}")
    Page<DoctorCard> findAllBy(Pageable pageable);

    @Override
    Page<DoctorCard> findAll(Pageable pageable);

    List<DoctorCard> getBySpecializations(String specialization);

    void deleteBy_id(ObjectId id);

}
