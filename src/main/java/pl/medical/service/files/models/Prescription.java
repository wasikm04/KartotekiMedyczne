package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Prescription {
    @Id
    private ObjectId _id;
    private long prescriptionId;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateTo;
    private String doctorFullName;
    private long numberPWZ; //in case of searching doctor
    private String departmentNFZ;
    private String permissions;
    private List<String> medicines;

    public  Prescription(){}

    public Prescription(long prescriptionId, Date dateFrom, Date dateTo, String doctorFullName, long numberPWZ, String departmentNFZ, String permissions, List<String> medicines) {
        this.prescriptionId = prescriptionId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.doctorFullName = doctorFullName;
        this.numberPWZ = numberPWZ;
        this.departmentNFZ = departmentNFZ;
        this.permissions = permissions;
        this.medicines = medicines;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
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

    public String getDepartmentNFZ() {
        return departmentNFZ;
    }

    public void setDepartmentNFZ(String departmentNFZ) {
        this.departmentNFZ = departmentNFZ;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public List<String> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<String> medicines) {
        this.medicines = medicines;
    }
}
