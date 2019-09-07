package pl.medical.service.files.repositories.patientcard;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.*;

import java.util.List;
import java.util.Optional;

@Component
public class PatientCardOperationsImpl implements PatientCardOperations {


    private MongoOperations mongo;
    private PatientCardRepository repository;

    public PatientCardOperationsImpl( MongoOperations mongo, PatientCardRepository repository){
        this.mongo = mongo;
        this.repository = repository;
    }

    @Override
    public void updateCardWithoutArrays(PatientCard updatedCard) {
        PatientCard card = repository.findBy_user_mail(updatedCard.get_user_mail());
        updatedCard.setMedicalTests(card.getMedicalTests());
        updatedCard.setPrescriptions(card.getPrescriptions());
        updatedCard.setReferrals(card.getReferrals());
        updatedCard.setTreatments(card.getTreatments());
        mongo.save(updatedCard);
    }

    @Override
    public MedicalTest findMedicalTestByUserMailAndId(String mail, ObjectId id) {
        PatientCard card = repository.findBy_user_mail(mail);
        if (card != null) {
            List<MedicalTest> tests = card.getMedicalTests();
            Optional<MedicalTest> test = tests.stream().filter(t -> t.get_id().equals(id)).findFirst();
            return test.orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public boolean saveMedicalTest(String mail, MedicalTest medtest) {
        return saveObject("medicalTests",mail,medtest);
    }

    @Override
    public void deleteMedicalTest(String mail, ObjectId id) {
        deleteObject("medicalTests",mail,id);
    }

    @Override
    public Prescription findPrescriptionByUserMailAndId(String mail, ObjectId id) {
        PatientCard card = repository.findBy_user_mail(mail);
        if (card != null) {
            List<Prescription> pres = card.getPrescriptions();
            Optional<Prescription> test = pres.stream().filter(t -> t.get_id().equals(id)).findFirst();
            return test.orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public boolean savePrescription(String mail, Prescription prescription) {
        return saveObject("prescriptions",mail,prescription);
    }

    @Override
    public void deletePrescription(String mail, ObjectId id) {
        deleteObject("prescriptions",mail,id);
    }

    @Override
    public Referral findReferralByUserMailAndId(String mail, ObjectId id) {
        PatientCard card = repository.findBy_user_mail(mail);
        if (card != null) {
            List<Referral> refs = card.getReferrals();
            Optional<Referral> test = refs.stream().filter(t -> t.get_id().equals(id)).findFirst();
            return test.orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public boolean saveReferral(String mail, Referral refferal) {
        return saveObject("referrals",mail,refferal);
    }

    @Override
    public void deleteReferral(String mail, ObjectId id) {
        deleteObject("referrals",mail,id);
    }


    //Metody obsługi Information(?) (add/update/delete/get)
    @Override
    public Treatment findTreatmentByUserMailAndId(String mail, ObjectId id) {
        PatientCard card = repository.findBy_user_mail(mail);
        if (card != null) {
            List<Treatment> treatments = card.getTreatments();
            Optional<Treatment> test = treatments.stream().filter(t -> t.get_id().equals(id)).findFirst();
            return test.orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public boolean saveTreatment(String mail, Treatment treatment) {
        return saveObject("treatments",mail,treatment);
    }

    @Override
    public void deleteTreatment(String mail, ObjectId id) {
        deleteObject("treatments",mail,id);
    }


    private void deleteObject(String fieldName, String mail, ObjectId id) {
        Update update = new Update().pull(fieldName,
                new BasicDBObject("_id", id));
        mongo.updateFirst(Query.query(Criteria.where("_user_mail").is(mail)), update, PatientCard.class);
    }

    private boolean saveObject(String fieldName, String mail, Object object) {
        UpdateResult res = mongo.updateFirst(
                Query.query(Criteria.where("_user_mail").is(mail)),
                new Update().push(fieldName, object), PatientCard.class);
        return res.wasAcknowledged();
    }
}

