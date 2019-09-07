package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MedicalTest {
    @Id
    private ObjectId _id;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate testDate;
    @NotNull
    private String testName;
    @NotNull
    private String author; //obiekt pracownika(?)
    @NotNull
    private List<String> parametersWithReference;

    public MedicalTest(){}

    public MedicalTest(LocalDate testDate, String testName, String author, List<String> parametersWithReference){
        this.setTestDate(testDate);
        this.setTestName(testName);
        this.setAuthor(author);
        this.setParametersWithReference(parametersWithReference);
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getParametersWithReference() {
        return parametersWithReference;
    }

    public void setParametersWithReference(List<String> parametersWithReference) {
        this.parametersWithReference = parametersWithReference;
    }
}
