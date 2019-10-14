package pl.medical.service.files.repositories.appointment;

import org.bson.types.ObjectId;

public interface AppointmentOperations {

    boolean updateAppointmentWithUserData(ObjectId appointmentId, String userMail);

}
