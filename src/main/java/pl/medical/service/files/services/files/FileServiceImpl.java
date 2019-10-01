package pl.medical.service.files.services.files;

import com.google.common.io.Files;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.medical.service.files.repositories.files.FileRepository;
import pl.medical.service.files.repositories.medicaltest.MedicalTestRepository;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;
    private MedicalTestRepository medicalTestRepository;
    private GridFsOperations operations;

    FileServiceImpl(FileRepository fileRepository,
                    MedicalTestRepository medicalTestRepository, GridFsOperations operations) {
        this.fileRepository = fileRepository;
        this.medicalTestRepository = medicalTestRepository;
        this.operations = operations;
    }

    @Override
    public void addFileWithUserId(MultipartFile file, ObjectId medicalTestId) {
        ObjectId fileId = fileRepository.saveFile(file);
        //MedicalTestRepository.attachFileIdToTest(medicalTestId)
    }

    @Override
    public ByteArrayResource getFileByIdAndUserID(ObjectId fileId, ObjectId userId) {
        //find in medicalTestRepository of this userId if such file id exists
        GridFSFile Gridfile = fileRepository.getFile(fileId);
        try {
            return new ByteArrayResource(Files.toByteArray(operations.getResource(Gridfile).getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
