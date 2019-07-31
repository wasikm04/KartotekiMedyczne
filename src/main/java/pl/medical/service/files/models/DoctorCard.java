package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//oddzielna kolekcja
@Document(collection="DoctorCards")
public class DoctorCard {
    @Id
    private ObjectId _id;
    private String _user_mail;
    private String first_name;
    private String last_name;
    private long numberPWZ;
    private String title;
    private List<String> specializations;
    private long phoneNumber;
    private String prescriptionRefundNumber;

    public DoctorCard(){}
    public DoctorCard(String _user_mail, String first_name, String last_name, long numberPWZ, String title, List<String> specializations, long phoneNumber, String prescriptionRefundNumber){
        this.set_user_mail(_user_mail);
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setNumberPWZ(numberPWZ);
        this.setTitle(title);
        this.setSpecializations(specializations);
        this.setPhoneNumber(phoneNumber);
        this.setPrescriptionRefundNumber(prescriptionRefundNumber);
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String get_user_mail() {
        return _user_mail;
    }

    public void set_user_mail(String _user_mail) {
        this._user_mail = _user_mail;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public long getNumberPWZ() {
        return numberPWZ;
    }

    public void setNumberPWZ(long numberPWZ) {
        this.numberPWZ = numberPWZ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPrescriptionRefundNumber() {
        return prescriptionRefundNumber;
    }

    public void setPrescriptionRefundNumber(String prescriptionRefundNumber) {
        this.prescriptionRefundNumber = prescriptionRefundNumber;
    }
}
