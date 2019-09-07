package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "Users")
public class User {

    @Id
    private ObjectId _id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private List<String> roles;

    public User(){}

    public User(String password, String email, List<String> roles){
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public ObjectId get_id() {
        return _id;
    }
}
