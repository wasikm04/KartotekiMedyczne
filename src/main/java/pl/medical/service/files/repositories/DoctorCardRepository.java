package pl.medical.service.files.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.medical.service.files.models.DoctorCard;
import pl.medical.service.files.models.User;

import java.util.List;

public interface DoctorCardRepository extends MongoRepository<DoctorCard,String> {
    DoctorCard getByNumberPWZ(Long number);
    DoctorCard getBy_user_mail(String email);
    DoctorCard getByFirst_nameOrLast_name(String name);
    List<DoctorCard> getBySpecializations(List<String> specializations);

    @Override
    <S extends DoctorCard> S save(S s); // save/update

    void deleteBy_id(ObjectId id);

}
