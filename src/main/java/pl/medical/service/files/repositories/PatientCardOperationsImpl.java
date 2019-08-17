package pl.medical.service.files.repositories;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.medical.service.files.models.*;

import java.util.List;
import java.util.Optional;

@Component
public class PatientCardOperationsImpl implements PatientCardOperations {

    @Autowired
    private MongoOperations mongo;

    @Override
    public MedicalTest findMedicalTestByUserMailAndId(String mail, ObjectId id) {
        PatientCard card = mongo.findOne(Query.query(Criteria.where("_user_mail").is(mail)), PatientCard.class);
        if (card != null) {
            List<MedicalTest> tests = card.getMedicalTests();
            Optional<MedicalTest> test = tests.stream().filter(t -> t.get_id().equals(id)).findFirst();
            return test.orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public Prescription findPrescriptionByUserMailAndId(String mail, ObjectId id) {
        PatientCard card = mongo.findOne(Query.query(Criteria.where("_user_mail").is(mail)), PatientCard.class);
        if (card != null) {
            List<Prescription> pres = card.getPrescriptions();
            Optional<Prescription> test = pres.stream().filter(t -> t.get_id().equals(id)).findFirst();
            return test.orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public Referral findReferralByUserMailAndId(String mail, ObjectId id) {
        PatientCard card = mongo.findOne(Query.query(Criteria.where("_user_mail").is(mail)), PatientCard.class);
        if (card != null) {
            List<Referral> refs = card.getReferrals();
            Optional<Referral> test = refs.stream().filter(t -> t.get_id().equals(id)).findFirst();
            return test.orElse(null);
        } else {
            return null;
        }
    }


    //Metody obsługi Information (add/update/delete/get)
    @Override
    public Treatment findTreatmentByUserMailAndId(String mail, ObjectId id) {
        PatientCard card = mongo.findOne(Query.query(Criteria.where("_user_mail").is(mail)), PatientCard.class);
        if (card != null) {
            List<Treatment> treatments = card.getTreatments();
            Optional<Treatment> test = treatments.stream().filter(t -> t.get_id().equals(id)).findFirst();
            return test.orElse(null);
        } else {
            return null;
        }
    }
/*
    @Override
    public PatientCard findPatientCardByEmail(String user_email) {
        Criteria usercrit = Criteria.where("email").is(user_email);
        Query query = Query.query(usercrit);
        User user = mongo.findOne(query, User.class);
        Criteria card = Criteria.where("_userid").is(user.get_id());
        query = Query.query(card);
        return mongo.findOne(query, PatientCard.class);
    }
*/
}

