package pl.medical.service.files.repositories.appointment;

import org.bson.types.ObjectId;
import pl.medical.service.files.models.Appointment;

import java.util.List;

public interface AppointmentOperations {
    void updateAppointmentWithUserData(ObjectId appointmentId, String userMail);

}
