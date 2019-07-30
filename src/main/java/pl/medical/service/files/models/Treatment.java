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
}
