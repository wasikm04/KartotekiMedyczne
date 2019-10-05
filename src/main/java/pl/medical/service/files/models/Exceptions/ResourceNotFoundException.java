package pl.medical.service.files.models.Exceptions;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
