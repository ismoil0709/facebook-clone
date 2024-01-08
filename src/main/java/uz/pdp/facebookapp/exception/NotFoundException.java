package uz.pdp.facebookapp.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String val) {
        super(val + " not found");
    }
}
