package pl.medical.service.files.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.Appointment;
import pl.medical.service.files.models.PatientCard;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, ObjectId>{
    Appointment getAppointmentBy_id(ObjectId id);
    List<Appointment> getAppointmentsByPatient_mail(String patientMail);
    List<Appointment> getAppointmentsByDoctor_mail(String doctorMail);
    //zapis,edycja,usuwanie (w AppointmentOperations)
}
