package pl.medical.service.files.repositories.doctorcard;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.DoctorCard;

import java.util.List;

@Repository
public interface DoctorCardRepository extends MongoRepository<DoctorCard,String>, DoctorCardOperations {
    DoctorCard getByNumberPWZ(Long numberPWZ);

    DoctorCard getByUserMail(String _user_mail);

    DoctorCard getByFirstNameOrderByLastName(String name);

    List<DoctorCard> getBySpecializations(String specialization);

    void deleteBy_id(ObjectId id);

}
