package pl.medical.service.files.services.files;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.medical.service.files.models.Exceptions.ResourceNotFoundException;
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
        try {
            medicalTestRepository.updateMedicalTestWithFileId(medicalTestId, fileId);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GridFsResource getFileByIdAndUserName(ObjectId fileId, String username) {
        GridFSFile Gridfile = null;
        try {
            Gridfile = fileRepository.getFile(fileId);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return operations.getResource(Gridfile);
    }
}
