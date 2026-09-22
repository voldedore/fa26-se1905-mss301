package vn.edu.fpt.mss.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, Integer id) {
        super(resource + " not found with id: " + id);
    }
}