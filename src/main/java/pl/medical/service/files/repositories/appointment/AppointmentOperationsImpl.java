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

    public AppointmentOperationsImpl(MongoOperations mongo) {
        this.mongo = mongo;
    }

    @Override
    public void updateAppointmentWithUserData(ObjectId appointmentId, String userMail) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(appointmentId));
        Appointment toChange = mongo.findOne(query, Appointment.class);
        if (toChange == null) {
            throw new IllegalArgumentException();
        }
        toChange.setPatientMail(userMail);
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
