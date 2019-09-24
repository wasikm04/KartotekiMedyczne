package pl.medical.service.files.repositories.files;

import org.springframework.data.mongodb.core.MongoOperations;

public class FileRepositoryImpl implements FileRepository {
    private MongoOperations mongo;
}
