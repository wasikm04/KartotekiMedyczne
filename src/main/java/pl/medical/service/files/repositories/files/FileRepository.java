package pl.medical.service.files.repositories.files;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;

@Repository
public interface FileRepository {
    ObjectId saveFile(MultipartFile file);

    GridFSFile getFile(ObjectId fileId) throws ResourceNotFoundException;
}
