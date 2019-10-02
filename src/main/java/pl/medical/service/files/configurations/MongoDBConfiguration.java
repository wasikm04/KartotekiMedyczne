package pl.medical.service.files.configurations;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
@EnableMongoRepositories("pl.medical.service.files.repositories")
public class MongoDBConfiguration extends AbstractMongoConfiguration {

//    @Value("${spring.data.mongodb.host}")
//    private String mongoHost;

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        return new MongoClient();
    } //host, port

    @Override
    protected String getDatabaseName() {
        return "MedicalFiles";
    }
}
