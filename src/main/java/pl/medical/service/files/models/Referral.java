package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//skierowanie
public class Referral {
    @Id
    private ObjectId _id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private String purpose; //cel: badania/poradnia specjalistyczna
    private String recognition;
    private String doctorFullName;
    private long numberPWZ; //in case of searching doctor


}
