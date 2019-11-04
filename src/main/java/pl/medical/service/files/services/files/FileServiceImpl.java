package pl.medical.service.files.services.files;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.repositories.files.FileRepository;
import pl.medical.service.files.repositories.medicaltest.MedicalTestRepository;
import pl.medical.service.files.repositories.patientcard.PatientCardRepository;

@Service
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;
    private MedicalTestRepository medicalTestRepository;
    private GridFsOperations operations;
    private PatientCardRepository patientCardRepository;

    FileServiceImpl(FileRepository fileRepository,
                    MedicalTestRepository medicalTestRepository,
                    GridFsOperations operations,
                    PatientCardRepository patientCardRepository) {
        this.fileRepository = fileRepository;
        this.medicalTestRepository = medicalTestRepository;
        this.operations = operations;
        this.patientCardRepository = patientCardRepository;
    }

    @Override
    public void addFileWithUserId(MultipartFile file, ObjectId medicalTestId, String userName) {
        ObjectId fileId = fileRepository.saveFile(file);
        //       try {
            medicalTestRepository.updateMedicalTestWithFileId(medicalTestId, fileId, userName);
//        } catch (ResourceNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public GridFsResource getFileByIdAndUserName(ObjectId fileId, String email) {
        PatientCard card = patientCardRepository.findByUserMail(email);
        boolean belongsToUser = card
                .getMedicalTests()
                .stream()
                .anyMatch(mt -> fileId.equals(mt.getFileId()));
        if (belongsToUser) {
            GridFSFile Gridfile = null;
            Gridfile = fileRepository.getFile(fileId);
            return operations.getResource(Gridfile);
        } else {
            throw new IllegalAccessError("You have no read rights to this file");
        }
    }
}
