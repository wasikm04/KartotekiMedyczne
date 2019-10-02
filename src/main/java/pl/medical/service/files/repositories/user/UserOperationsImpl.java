package pl.medical.service.files.repositories.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.medical.service.files.models.User;

import java.util.Arrays;

@Component
public class UserOperationsImpl implements UserOperations {

    private MongoOperations mongo;
    private PasswordEncoder passwordEncoder;

    public UserOperationsImpl(
            MongoOperations mongo,
            PasswordEncoder passwordEncoder) {
        this.mongo = mongo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User model) {
        String password = passwordEncoder.encode(model.getPassword());
        User user = User.builder()
                .email(model.getEmail())
                .password(password)
                .roles(Arrays.asList("ROLE_USER"))
                // ._id(ObjectId.get()) //autogenerate?
                .build();

        return mongo.save(user);
    }

    @Override
    public void updateMail(ObjectId userid, String mail) {
        Query query = new Query();
        User toUpdate = mongo.findOne(query.addCriteria(Criteria.where("_id").is(userid)), User.class);
        toUpdate.setEmail(mail);
        mongo.save(toUpdate);
    }
}
