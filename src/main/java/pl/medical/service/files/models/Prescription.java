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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateTo;
    private String doctorFullName;
    private long numberPWZ; //in case of searching doctor
    private String departmentNFZ;
    private String permissions;
    private List<String> medicines;

}
