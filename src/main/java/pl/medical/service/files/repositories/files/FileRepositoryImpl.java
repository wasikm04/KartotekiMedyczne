package pl.medical.service.files.repositories.files;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
                    file.getInputStream(), file.getName(), file.getContentType(), metaData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public GridFSFile getFile(ObjectId fileId) {
        GridFSFile file = gridOperations.findOne(new Query(Criteria.where("_id").is(fileId)));
        return file;
    }
}
