package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Information {

    @Id
    private ObjectId _id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private String information;
    private String doctorFullName;
    private long numberPWZ; //in case of searching doctor

    public Information(){}

    public Information(Date date, String information, String doctorFullName, long numberPWZ){
        this.setDate(date);
        this.setInformation(information);
        this.setDoctorFullName(doctorFullName);
        this.setNumberPWZ(numberPWZ);
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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
