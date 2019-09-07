package pl.medical.service.files.repositories.appointment;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.Appointment;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, ObjectId>, AppointmentOperations{
    Appointment getAppointmentBy_id(ObjectId id);
    List<Appointment> getAppointmentsByPatient_mail(String patientMail);
    List<Appointment> getAppointmentsByDoctor_mail(String doctorMail);
    void deleteBy_id(ObjectId id);
}
