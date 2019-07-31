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
    private String address;
    private long phoneNumber;
    private String insuranceType; //active/passive
    private List<Prescription> prescriptions; //recepty
    private List<MedicalTest> medicalTests; //badania
    private List<Referral> referrals; //skierowania
    private List<Treatment> treatments; //choroby i zalecenia


    public PatientCard() {}

    public PatientCard(String firstName, String lastName, String _user_mail, Date dateBirth, char sex, long PESEL, String address, long phoneNumber, String insuranceType, List<Prescription> prescriptions, List<MedicalTest> medicalTests, List<Referral> referrals, List<Treatment> treatments) {
        this.first_name = firstName;
        this.last_name = lastName;
        this._user_mail = _user_mail;
        this.dateBirth = dateBirth;
        this.sex = sex;
        this.PESEL = PESEL;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.insuranceType = insuranceType;
        this.prescriptions = prescriptions;
        this.medicalTests = medicalTests;
        this.referrals = referrals;
        this.treatments = treatments;
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

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public long getPESEL() {
        return PESEL;
    }

    public void setPESEL(long PESEL) {
        this.PESEL = PESEL;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<MedicalTest> getMedicalTests() {
        return medicalTests;
    }

    public void setMedicalTests(List<MedicalTest> medicalTests) {
        this.medicalTests = medicalTests;
    }

    public List<Referral> getReferrals() {
        return referrals;
    }

    public void setReferrals(List<Referral> referrals) {
        this.referrals = referrals;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }
}
