package pl.medical.service.files.services.files;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void addFileWithUserId(MultipartFile file, ObjectId medicalTestId, String userName);
    GridFsResource getFileByIdAndUserName(ObjectId fileId, String userName);
}
