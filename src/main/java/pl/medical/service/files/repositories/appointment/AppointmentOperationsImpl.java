package pl.medical.service.files.repositories.appointment;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.Appointment;
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;
import pl.medical.service.files.repositories.user.UserRepository;

import java.util.Optional;

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
    public boolean updateAppointmentWithUserData(Appointment appointment) { //ObjectId appointmentId, String userMail
        //Query query = new Query();
        //query.addCriteria(Criteria.where("_id").is(appointmentId));
        //Appointment toChange = mongo.findOne(query, Appointment.class);
        if (appointment == null || appointment.getPatientMail() != null) {
            throw new ResourceNotFoundException("Brak takiego terminu lub został on już zarezerwowany");
            //} else if (!userRepository.existsUserByEmail(userMail)) {
            //    throw new ResourceNotFoundException("Brak użytkownika o podanym mailu " + userMail);
        } else {
            //toChange.setPatientMail(userMail);
            return Optional.ofNullable(mongo.save(appointment)).isPresent();
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
