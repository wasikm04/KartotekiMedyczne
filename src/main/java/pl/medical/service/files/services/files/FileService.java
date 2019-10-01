package pl.medical.service.files.services.files;

import org.bson.types.ObjectId;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void addFileWithUserId(MultipartFile file, ObjectId medicalTestId);

    ByteArrayResource getFileByIdAndUserID(ObjectId fileId, ObjectId userId);
}
