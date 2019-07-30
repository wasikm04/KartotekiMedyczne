package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Document(collection="PatientCards")
public class PatientCard {
    @Id
    private ObjectId _id;
    private String _user_mail;
    private String first_name;
    private String last_name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateBirth;
    private char sex; //m/w
    private long PESEL;
    private String Address;
    private long phoneNumber;
    private String insuranceType; //active/passive
    private List<Prescription> prescriptions; //recepty
    private List<MedicalTest> medicalTests; //badania
    private List<Referral> referrals; //skierowania
    private List<Treatment> treatments; //choroby i zalecenia


    public PatientCard() {}

    public PatientCard(String firstName, String lastName, String _username) {
        this.first_name = firstName;
        this.last_name = lastName;
        this._user_mail = _username;
    }

    public String get_id() { return _id.toHexString(); }

    public void set_id(ObjectId _id) { this._id = _id; }

    public String get_user_mail() {
        return _user_mail;
    }

    public void set_user_mail(String _user_mail) {
        this._user_mail = _user_mail;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }


    @Override
    public String toString() {
        return String.format(
                "PatientCard[id=%s, user_id ='%s' firstName='%s', lastName='%s']",
                _id, get_user_mail(), first_name, last_name);
    }

}
