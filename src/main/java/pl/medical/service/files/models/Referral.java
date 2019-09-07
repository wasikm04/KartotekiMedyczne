package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

//skierowanie
public class Referral {
    @Id
    private ObjectId _id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private String purpose; //cel: badania/poradnia specjalistyczna
    private String recognition;
    @NotNull
    private String doctorFullName;
    @NotNull
    private long numberPWZ; //in case of searching doctor

    public Referral() {
    }

    public Referral(LocalDate date, String purpose, String recognition, String doctorFullName, long numberPWZ) {
        this.date = date;
        this.purpose = purpose;
        this.recognition = recognition;
        this.doctorFullName = doctorFullName;
        this.numberPWZ = numberPWZ;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public long getNumberPWZ() {
        return numberPWZ;
    }

    public void setNumberPWZ(long numberPWZ) {
        this.numberPWZ = numberPWZ;
    }
}
