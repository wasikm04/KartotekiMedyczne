package pl.medical.service.files.services.appointment;

import org.springframework.stereotype.Service;
import pl.medical.service.files.repositories.appointment.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
}
