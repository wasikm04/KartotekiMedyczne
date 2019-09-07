package pl.medical.service.files.repositories.doctorcard;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.medical.service.files.models.DoctorCard;

import java.util.List;

public interface DoctorCardRepository extends MongoRepository<DoctorCard,String> {
    DoctorCard getByNumberPWZ(Long number);
    DoctorCard getBy_user_mail(String mail);
    DoctorCard getByFirst_nameOrLast_name(String name);
    List<DoctorCard> getBySpecializations(List<String> specializations);


    void deleteBy_id(ObjectId id);

}
