package pl.medical.service.files.controllers;

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
import pl.medical.service.files.services.files.MediaTypeUtils;

import javax.servlet.ServletContext;
import java.io.IOException;

@RestController
public class FilesController {

    private ServletContext servletContext;
    private FileService fileService;

    FilesController(ServletContext servletContext,
                    FileService fileService) {
        this.servletContext = servletContext;
        this.fileService = fileService;
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId, Authentication authentication) throws IOException {
        ObjectId id = new ObjectId(fileId);
        GridFsResource gridFsResource = fileService.getFileByIdAndUserName(id, authentication.getName());
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(servletContext, gridFsResource.getFilename()); //do usunięcia
        byte[] array = new byte[gridFsResource.getInputStream().available()];
        gridFsResource.getInputStream().read(array);
        ByteArrayResource resource = new ByteArrayResource(array);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + gridFsResource.getFilename())
                .contentType(MediaType.parseMediaType(gridFsResource.getContentType())) //
                .contentLength(gridFsResource.contentLength()) //
                .body(resource);
    }

    @PostMapping("/upload/{medicalTestId}")
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile imageData, @PathVariable String medicalTestId, Authentication authentication) throws IOException {
        fileService.addFileWithUserId(imageData, new ObjectId(medicalTestId));
        return ResponseEntity.ok("Dodano dokument z wynikami badań");
    }

}
