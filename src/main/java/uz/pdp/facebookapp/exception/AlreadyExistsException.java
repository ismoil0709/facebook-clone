package uz.pdp.facebookapp.exception;

public class AlreadyExistsException extends RuntimeException{

    public AlreadyExistsException(String val) {
        super(val + " already exists");
    }
}
