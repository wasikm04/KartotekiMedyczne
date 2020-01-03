package pl.medical.service.files.repositories.files;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface FileRepository {
    ObjectId saveFile(MultipartFile file);

    GridFsResource getFile(ObjectId fileId);
}
