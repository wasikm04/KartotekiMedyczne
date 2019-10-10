package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.AppointmentDto;
import pl.medical.service.files.models.Appointment;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface AppointmentMapper {

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy HH:mm")
    AppointmentDto mapToAppointmentDto(Appointment appointment);

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy HH:mm")
    Appointment mapToAppointment(AppointmentDto appointmentDto);

}
