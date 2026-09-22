package vn.edu.fpt.mss.exception;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String service, Integer id) {
        super(service + " unreachable, cannot resolve id: " + id);
    }
}
