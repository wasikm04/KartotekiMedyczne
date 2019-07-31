package pl.medical.service.files.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

//choroba i zalecenia do niej
public class Treatment {

    @Id
    private ObjectId _id;
    private String numberICD;
    private List<Information> symptomsAndDiagnosis; //objawy i kolejne diagnozy
    private List<Information> pharmacotherapy; //podane leki i przepisane
    private List<Information> medicalAnalysisAndRecommendations; //epikryza, opis kroków i rozpoznania

    public Treatment() {
    }

    public Treatment(String numberICD, List<Information> symptomsAndDiagnosis, List<Information> pharmacotherapy, List<Information> medicalAnalysisAndRecommendations) {
        this.numberICD = numberICD;
        this.symptomsAndDiagnosis = symptomsAndDiagnosis;
        this.pharmacotherapy = pharmacotherapy;
        this.medicalAnalysisAndRecommendations = medicalAnalysisAndRecommendations;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getNumberICD() {
        return numberICD;
    }

    public void setNumberICD(String numberICD) {
        this.numberICD = numberICD;
    }

    public List<Information> getSymptomsAndDiagnosis() {
        return symptomsAndDiagnosis;
    }

    public void setSymptomsAndDiagnosis(List<Information> symptomsAndDiagnosis) {
        this.symptomsAndDiagnosis = symptomsAndDiagnosis;
    }

    public List<Information> getPharmacotherapy() {
        return pharmacotherapy;
    }

    public void setPharmacotherapy(List<Information> pharmacotherapy) {
        this.pharmacotherapy = pharmacotherapy;
    }

    public List<Information> getMedicalAnalysisAndRecommendations() {
        return medicalAnalysisAndRecommendations;
    }

    public void setMedicalAnalysisAndRecommendations(List<Information> medicalAnalysisAndRecommendations) {
        this.medicalAnalysisAndRecommendations = medicalAnalysisAndRecommendations;
    }
}
