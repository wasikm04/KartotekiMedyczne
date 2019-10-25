package pl.medical.service.files.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Document(collection="DoctorCards")
//@CompoundIndex(name = "first_last_name", def = "{'firstName' : 1, 'lastName': 1, 'userMail' :1 }")
public class DoctorCard {
    @Id
    private ObjectId _id;
    @NotNull
//  @TextIndexed
    @Indexed
    private String userMail;
    @NotNull
//  @TextIndexed
    @Indexed
    private String firstName;
    @NotNull
//  @TextIndexed
    @Indexed
    private String lastName;
    @NotNull
    private long numberPWZ;
    private String title;
    @Indexed
    private List<String> specializations;
    @NotNull
    private long phoneNumber;
    @NotNull
    private String prescriptionRefundNumber;
}
