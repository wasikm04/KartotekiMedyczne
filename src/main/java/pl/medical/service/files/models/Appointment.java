package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//wizyta u lekarza | oddzielny kolekcja
@Document(collection="Appointments")
public class Appointment {
    @Id
    private ObjectId _id;
    private String patient_mail;
    private String doctor_mail;
    private String patientFullName;
    private String doctorFullName;
    @DateTimeFormat(pattern = "dd/MM/yyyy  HH:mm")
    private Date date;
    private String comment;

    public Appointment(){}
    public Appointment(String patient_mail, String doctor_mail, Date date, String comment, String doctorFullName, String patientFullName){
        this.patient_mail = patient_mail;
        this.doctor_mail = doctor_mail;
        this.date = date;
        this.comment = comment;
        this.patientFullName = patientFullName;
        this.doctorFullName = doctorFullName;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getPatient_mail() {
        return patient_mail;
    }

    public void setPatient_mail(String patient_mail) {
        this.patient_mail = patient_mail;
    }

    public String getDoctor_mail() {
        return doctor_mail;
    }

    public void setDoctor_mail(String doctor_mail) {
        this.doctor_mail = doctor_mail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
