package pl.medical.service.files.repositories.appointment;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.Appointment;

import java.util.List;

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

    }

    @Override
    public List<Appointment> getFreeAppointmentsByDoctorMail(String doctorMail) {
        return null;
    }
}
