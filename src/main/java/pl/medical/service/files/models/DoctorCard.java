package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

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
}
