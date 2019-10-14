package pl.medical.service.files.configurations;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("pl.medical.service.files.repositories")
public class MongoDBConfiguration extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI(mongoUri));
    }

    @Override
    protected String getDatabaseName() {
        return "Files";
    }
}
