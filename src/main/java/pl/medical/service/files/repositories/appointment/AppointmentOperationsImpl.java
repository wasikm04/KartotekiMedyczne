package pl.medical.service.files.repositories.appointment;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.Appointment;
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;
import pl.medical.service.files.repositories.user.UserRepository;

@Component
public class AppointmentOperationsImpl implements AppointmentOperations {

    private MongoOperations mongo;
    private UserRepository userRepository;

    public AppointmentOperationsImpl(MongoOperations mongo,
                                     UserRepository userRepository) {
        this.mongo = mongo;
        this.userRepository = userRepository;
    }

    @Override
    public void updateAppointmentWithUserData(ObjectId appointmentId, String userMail) throws ResourceNotFoundException {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(appointmentId));
        Appointment toChange = mongo.findOne(query, Appointment.class);
        if (toChange == null || toChange.getPatientMail() != null) {
            throw new ResourceNotFoundException("Brak takiego terminu lub został on już zarezerwowany");
        } else if (!userRepository.existsUserByEmail(userMail)) {
            throw new ResourceNotFoundException("Brak użytkownika o podanym mailu " + userMail);
        } else {
            toChange.setPatientMail(userMail);
            mongo.save(toChange);
        }
    }

//    @Override
//    public List<Appointment> getFreeAppointmentsByDoctorMail(String doctorMail) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("available").is(false));
//        Predicate  predicate = new Appointment().isAvailable();
//        repository.findAll(query);
//        return null;
//    }
}
