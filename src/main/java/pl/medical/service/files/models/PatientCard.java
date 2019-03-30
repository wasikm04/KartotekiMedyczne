package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="KartotekiPacjentow")
public class PatientCard {
    @Id
    private ObjectId _id;
    //private ObjectId _userid;
    private String _username;
    private String first_name;
    private String last_name;
    private String key;

    public PatientCard() {}

    public PatientCard(String firstName, String lastName, String key, String _username) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.key = key;
        this._username = _username;
    }

    public String get_id() { return _id.toHexString(); }

    public void set_id(ObjectId _id) { this._id = _id; }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return String.format(
                "PatientCard[id=%s, user_id ='%s' firstName='%s', lastName='%s', key='%s']",
                _id, get_username(), first_name, last_name, key);
    }

}
