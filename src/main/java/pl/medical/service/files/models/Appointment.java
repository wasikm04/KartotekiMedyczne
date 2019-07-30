package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//wizyta u lekarza
public class Appointment {
    @Id
    private ObjectId _id;
    private String patient_mail;
    private String doctor_mail;
    @DateTimeFormat(pattern = "dd/MM/yyyy  HH:mm")
    private Date date;
    private String comment;
}
