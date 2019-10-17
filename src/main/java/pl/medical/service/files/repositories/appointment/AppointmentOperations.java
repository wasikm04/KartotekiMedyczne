package pl.medical.service.files.repositories.appointment;

import pl.medical.service.files.models.Appointment;

public interface AppointmentOperations {

    boolean updateAppointmentWithUserData(Appointment appointment);

}
