package pl.medical.service.files.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.medical.service.files.services.files.FileService;

import java.io.IOException;

@Api(value = "Pliki", description = "Operacje dodawania i pobierania plików badań medycznych")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class FilesController {

    private FileService fileService;

    FilesController(FileService fileService) {
        this.fileService = fileService;
    }


    @ApiOperation(value = "Zapisz plik")
    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId, Authentication authentication) throws IOException {
        ObjectId id = new ObjectId(fileId);
        GridFsResource gridFsResource = fileService.getFileByIdAndUserName(id, authentication.getPrincipal().toString());
        byte[] array = IOUtils.toByteArray(gridFsResource.getInputStream());

        ByteArrayResource resource = new ByteArrayResource(array);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + gridFsResource.getFilename())
                .contentType(MediaType.parseMediaType(gridFsResource.getContentType()))
                .contentLength(gridFsResource.contentLength())
                .body(resource);
    }


    @ApiOperation(value = "Pobierz plik")
    @PostMapping("/upload/{medicalTestId}")
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile imageData, @PathVariable String medicalTestId, Authentication authentication) {
        fileService.addFileWithUserId(imageData, new ObjectId(medicalTestId), authentication.getPrincipal().toString());
        return ResponseEntity.ok("Dodano dokument z wynikami badań");
    }

}
