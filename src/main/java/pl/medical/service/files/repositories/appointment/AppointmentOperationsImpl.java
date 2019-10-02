package pl.medical.service.files.repositories.appointment;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.Appointment;

import java.util.List;
import java.util.function.Predicate;

@Component
public class AppointmentOperationsImpl implements AppointmentOperations {

    private MongoOperations mongo;
    private AppointmentRepository repository;


    public AppointmentOperationsImpl(MongoOperations mongo,
                                     AppointmentRepository repository) {
        this.mongo = mongo;
        this.repository = repository;
    }

    @Override
    public void updateAppointmentWithUserData(ObjectId appointmentId, String userMail) {
        Appointment toChange = repository.getAppointmentBy_id(appointmentId);
        toChange.setPatient_mail(userMail);
        mongo.save(toChange);
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
