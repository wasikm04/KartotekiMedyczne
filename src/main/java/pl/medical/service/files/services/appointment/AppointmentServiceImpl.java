package pl.medical.service.files.services.appointment;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import pl.medical.service.files.repositories.appointment.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private MongoOperations mongo;

    public AppointmentServiceImpl(MongoOperations mongo) {
        this.mongo = mongo;
    }
}
