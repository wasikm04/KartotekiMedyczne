package pl.medical.service.files.repositories.doctorcard;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.DoctorCard;

import java.util.List;

@Repository
public interface DoctorCardRepository extends MongoRepository<DoctorCard,String>, DoctorCardOperations {
    DoctorCard getByNumberPWZ(Long number);

    DoctorCard getBy_user_mail(String mail);

    DoctorCard getByFirst_nameOrLast_name(String name);

    List<DoctorCard> getBySpecializations(List<String> specializations);

    void deleteBy_id(ObjectId id);

}
