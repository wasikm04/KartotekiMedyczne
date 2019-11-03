package pl.medical.service.files.services.appointment;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import pl.medical.service.files.models.Appointment;
import pl.medical.service.files.repositories.appointment.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAppointmentsForPatientMail(String patientMail) {
        return appointmentRepository.getAppointmentsByPatientMail(patientMail);
    }

    @Override
    public List<Appointment> getListOfFreeAppointmentsOfDoctorMail(String mail) {
        return appointmentRepository.getAppointmentsByPatientMailIsNullOrPatientMailIsAndDoctorMail("", mail);
    }

    @Override
    public List<Appointment> getAppointmentsForDoctorMail(String doctorMail) {
        return appointmentRepository.getAppointmentsByDoctorMail(doctorMail);
    }

    @Override
    public boolean updateAppointmentWithPatientData(Appointment appointment) {
        return appointmentRepository.updateAppointmentWithUserData(appointment);
    }

    @Override
    public boolean createNewAppointment(Appointment appointment) {
        return Optional.ofNullable(appointmentRepository.save(appointment)).isPresent();
    }

    @Override
    public boolean deleteAppointment(ObjectId id) {
        return appointmentRepository.deleteBy_id(id);
    }
}
