package pl.medical.service.files.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
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
@Document(collection = "Users")
public class User {

    @Id
    private ObjectId _id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private List<String> roles;
}
