package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Prescription {
    @Id
    private ObjectId _id;
    private long prescriptionId;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFrom;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateTo;
    private String doctorFullName;
    @NotNull
    private long numberPWZ; //in case of searching doctor
    private String departmentNFZ;
    private String permissions;
    @NotNull
    private List<String> medicines;

    public  Prescription(){}

    public Prescription(long prescriptionId, LocalDate dateFrom, LocalDate dateTo, String doctorFullName, long numberPWZ, String departmentNFZ, String permissions, List<String> medicines) {
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

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
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
