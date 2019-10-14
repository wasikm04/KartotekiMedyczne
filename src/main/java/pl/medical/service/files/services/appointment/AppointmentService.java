package pl.medical.service.files.services.appointment;


import org.bson.types.ObjectId;
import pl.medical.service.files.models.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAppointmentsForPatientMail(String patientMail);

    List<Appointment> getListOfFreeAppointmentsOfDoctorMail(String mail);

    List<Appointment> getAppointmentsForDoctorMail(String doctorMail);

    boolean updateAppointmentWithPatientData(String userMail, ObjectId appointmentId);

    boolean createNewAppointment(Appointment appointment);

    boolean deleteAppointment(ObjectId id);
}
