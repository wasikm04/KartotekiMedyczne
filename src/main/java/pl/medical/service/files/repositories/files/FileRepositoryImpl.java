package pl.medical.service.files.repositories.files;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;

import java.io.IOException;

@Component
public class FileRepositoryImpl implements FileRepository {

    private GridFsOperations gridOperations;

    FileRepositoryImpl(GridFsOperations gridOperations) {
        this.gridOperations = gridOperations;
    }

    public ObjectId saveFile(MultipartFile file) {
        DBObject metaData = new BasicDBObject();
        ObjectId id = null;
        try {
            id = gridOperations.store(
                    file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public GridFsResource getFile(ObjectId fileId) {
        GridFSFile file = gridOperations.findOne(new Query(Criteria.where("_id").is(fileId)));
        if (file == null) {
            throw new ResourceNotFoundException("Brak pliku z badaniami o podanym id");
        }
        return gridOperations.getResource(file);
    }
}
